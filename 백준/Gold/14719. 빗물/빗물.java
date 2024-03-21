import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int H, W;
    static int[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        board = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int result = 0;
        for (int current = 1; current < W - 1; current++) {
            int left = 0, right = 0;

            for (int j = 0; j < current; j++) {
                left = Math.max(board[j], left);
            }
            for (int j = current + 1; j < W; j++) {
                right = Math.max(board[j], right);
            }

            if (board[current] < left && board[current] < right) {
                result += Math.min(left, right) - board[current];
            }
        }
        System.out.println(result);
    }
}
