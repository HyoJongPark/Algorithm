import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, K, result = Integer.MIN_VALUE;
    static int[][] board;
    static int[][] check;

    static int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        check = new int[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                checkBoard(i, j, 1);
                dfs(1, i, j, board[i][j]);
                checkBoard(i, j, -1);
            }
        }
        System.out.println(result);
    }

    private static void dfs(int level, int x, int y, int value) {
        if (level == K) {
            result = Math.max(result, value);
            return;
        }

        for (int i = x; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (check[i][j] == 0) {
                    checkBoard(i, j, 1);
                    dfs(level + 1, i, j, value + board[i][j]);
                    checkBoard(i, j, -1);
                }
            }
        }
    }

    private static void checkBoard(int x, int y, int value) {
        check[x][y] += value;
        for (int i = 0; i < d.length; i++) {
            int nextX = x + d[i][0];
            int nextY = y + d[i][1];

            if (isValid(nextX, nextY)) {
                check[nextX][nextY] += value;
            }
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }
}