import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, R;
    static int[][] board;
    static boolean[][][] isRoad;
    static Point[] cows;

    static final int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        isRoad = new boolean[N + 1][N + 1][4];
        board = new int[N + 1][N + 1];
        cows = new Point[K + 1];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for (int j = 0; j < 4; j++) {
                if (x1 + d[j][0] == x2 && y1 + d[j][1] == y2) {
                    isRoad[x1][y1][j] = true;
                    isRoad[x2][y2][(j + 2) % 4] = true;
                    break;
                }
            }
        }

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            board[cows[i].x][cows[i].y] = i;
        }

        int result = 0;
        for (int i = 1; i <= K; i++) {
            result += (K - bfs(cows[i]));
        }
        System.out.println(result / 2);
    }

    private static int bfs(Point cow) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N + 1][N + 1];

        q.offer(cow);
        visited[cow.x][cow.y] = true;
        int count = 1;
        while (!q.isEmpty()) {
            Point current = q.poll();

            for (int i = 0; i < 4; i++) {
                if (isRoad[current.x][current.y][i]) {
                    continue;
                }

                int nextX = current.x + d[i][0];
                int nextY = current.y + d[i][1];
                if (isValid(nextX, nextY) && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    q.offer(new Point(nextX, nextY));

                    if (board[nextX][nextY] != 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 < nextX && nextX <= N && 0 < nextY && nextY <= N;
    }

    static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
