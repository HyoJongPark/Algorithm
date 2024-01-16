import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M, K;
    static int[][][] check;
    static char[][] board;
    static int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        check = new int[N][M][80 + 1];
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        char[] target = br.readLine().toCharArray();
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == target[0]) {
                    check[i][j][0] = 1;
                    count += dfs(1, i, j, target);
                }
            }
        }
        System.out.println(count);
    }

    private static int dfs(int level, int x, int y, char[] target) {
        if (level == target.length) {
            return 1;
        }

        int count = 0;
        for (int[] direction : d) {
            for (int i = 1; i <= K; i++) {
                int nextX = x + direction[0] * i;
                int nextY = y + direction[1] * i;

                if (isValid(nextX, nextY) && board[nextX][nextY] == target[level] && check[nextX][nextY][level] != -1) {
                    if (check[nextX][nextY][level] != 0) {
                        count += check[nextX][nextY][level];
                        continue;
                    }

                    int curr = dfs(level + 1, nextX, nextY, target);
                    if (curr == 0) {
                        check[nextX][nextY][level] = -1;
                    } else {
                        check[nextX][nextY][level] = curr;
                        count += check[nextX][nextY][level];
                    }
                }
            }
        }
        return count;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }
}
