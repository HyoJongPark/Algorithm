import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int T, H, W;
    static char[][] board;
    static boolean[][] check;

    static final int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            board = new char[H][W];
            check = new boolean[H][W];
            for (int i = 0; i < H; i++) {
                board[i] = br.readLine().toCharArray();
            }

            int count = 0;
            for (int i = 0; i < H; i++) {
                for (int j = 0; j < W; j++) {
                    if (!check[i][j] && board[i][j] == '#') {
                        count++;
                        BFS(i, j);
                    }
                }
            }
            sb.append(count).append("\n");
        }
        System.out.print(sb);
    }

    private static void BFS(int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        check[x][y] = true;
        q.offer(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current[0] + d[i][0];
                int nextY = current[1] + d[i][1];

                if (isValid(nextX, nextY) && !check[nextX][nextY] && board[nextX][nextY] == '#') {
                    check[nextX][nextY] = true;
                    q.offer(new int[]{nextX, nextY});
                }
            }
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < H && 0 <= nextY && nextY < W;
    }
}
