import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static java.lang.Math.max;

class Main {
    static int N, M, result = Integer.MIN_VALUE;
    static char[][] board;
    static int[][] dp;
    static boolean[][] check;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        //Input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        dp = new int[N][M];
        check = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        check[0][0] = true;
        dp[0][0] = 1;
        DFS(1, 0, 0);
        System.out.println(result);
    }

    public static void DFS(int level, int x, int y) {
        int coin = board[x][y] - '0';
        for (int i = 0; i < 4; i++) {
            int nextX = x + coin * dx[i];
            int nextY = y + coin * dy[i];

            if (!isValid(nextX, nextY) || board[nextX][nextY] == 'H') {
                result = max(result, level);
            } else if (check[nextX][nextY]) {
                System.out.println(-1);
                System.exit(0);
            } else if (dp[nextX][nextY] < level) {
                dp[nextX][nextY] = level;
                check[nextX][nextY] = true;
                DFS(level + 1, nextX, nextY);
                check[nextX][nextY] = false;
            }
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }
}