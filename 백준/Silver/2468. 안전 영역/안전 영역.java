import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] board;
    static boolean[][] check;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] > maxHeight) {
                    maxHeight = board[i][j];
                }
            }
        }

        int result = 0;
        for (int height = 0; height <= maxHeight; height++) {
            check = new boolean[N][N];
            int cnt = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!check[i][j] && board[i][j] > height) {
                        check[i][j] = true;
                        cnt += DFS(i, j, height);
                    }
                }
            }
            result = Math.max(result, cnt);
        }
        System.out.println(result);
    }

    static int DFS(int x, int y, int height) {
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (isValid(nextX, nextY) && board[nextX][nextY] > height) {
                check[nextX][nextY] = true;
                DFS(nextX, nextY, height);
            }
        }
        return 1;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < N
                && !check[nextX][nextY];
    }
}