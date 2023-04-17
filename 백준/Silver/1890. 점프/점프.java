import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int N;
    static int[][] board;
    static long[][] dp;
    static int[][] d = {{1, 0}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        dp = new long[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dp[0][0] = 1;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (board[x][y] != 0 && dp[x][y] != 0) {
                    move(x, y);
                }
            }
        }
        System.out.println(dp[N - 1][N - 1]);
    }

    private static void move(int x, int y) {
        for (int i = 0; i < 2; i++) {
            int nextX = x + board[x][y] * d[i][0];
            int nextY = y + board[x][y] * d[i][1];

            if (isValid(nextX, nextY)) {
                dp[nextX][nextY] += dp[x][y];
            }
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < N;
    }
}