import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, B;
    static int answerHeight = Integer.MIN_VALUE, answerTime = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int currentNum = Integer.parseInt(st.nextToken());
                board[i][j] = currentNum;
                max = Math.max(max, currentNum);
                min = Math.min(min, currentNum);
            }
        }

        findAnswer();
        System.out.printf("%d %d", answerTime, answerHeight);
    }

    private static void findAnswer() {
        for (int height = min; height <= max; height++) {
            int currentTime = pickingTheGround(height);

            if (currentTime <= answerTime) {
                answerTime = currentTime;
                answerHeight = height;
            }
        }
    }

    private static int pickingTheGround(int targetHeight) {
        int time = 0, numberOfBlock = B;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int currentHeight = board[i][j];
                if (currentHeight > targetHeight) {
                    time += 2 * (currentHeight - targetHeight);
                    numberOfBlock += currentHeight - targetHeight;
                } else if (currentHeight < targetHeight) {
                    time += targetHeight - currentHeight;
                    numberOfBlock -= targetHeight - currentHeight;
                }
            }
        }
        return numberOfBlock >= 0 ? time : Integer.MAX_VALUE;
    }
}
