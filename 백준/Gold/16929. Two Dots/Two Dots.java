import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static char[][] board;
    static boolean[][] check;

    static final int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        check = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                check[i][j] = true;
                if (DFS(1, new int[]{i, j}, new int[]{i, j})) {
                    System.out.println("Yes");
                    System.exit(0);
                }
                check[i][j] = false;
            }
        }
        System.out.println("No");
    }

    private static boolean DFS(int level, int[] currentPos, int[] startPos) {
        int targetColor = board[startPos[0]][startPos[1]];
        boolean result = false;
        for (int i = 0; i < 4; i++) {
            int nextX = currentPos[0] + d[i][0];
            int nextY = currentPos[1] + d[i][1];

            if (isValid(nextX, nextY) && !check[nextX][nextY] && board[nextX][nextY] == targetColor) {
                check[nextX][nextY] = true;
                result = DFS(level + 1, new int[]{nextX, nextY}, startPos);
                check[nextX][nextY] = false;
            }

            if (result || (level >= 4 && nextX == startPos[0] && nextY == startPos[1])) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }
}
