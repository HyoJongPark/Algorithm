import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int R, C, K;
    static int[][] distance;
    static char[][] board;
    static boolean[][] check;

    static final int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        distance = new int[R][C];
        board = new char[R][C];
        check = new boolean[R][C];

        for (int i = R - 1; i >= 0; i--) {
            board[i] = br.readLine().toCharArray();
        }

        check[0][0] = true;
        DFS(1, 0, 0);
        System.out.println(distance[0][0]);
    }

    private static int DFS(int level, int x, int y) {
        if (x == R - 1 && y == C - 1) {
            if (level == K) return 1;
            else return 0;
        }
        if (level > K) return 0;

        int result = 0;
        for (int i = 0; i < d.length; i++) {
            int nextX = x + d[i][0];
            int nextY = y + d[i][1];

            if (isValid(nextX, nextY) && !check[nextX][nextY] && board[nextX][nextY] != 'T') {
                check[nextX][nextY] = true;
                result += DFS(level + 1, nextX, nextY);
                check[nextX][nextY] = false;
            }
        }

        distance[x][y] += result;
        return result;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < R && 0 <= nextY && nextY < C;
    }
}