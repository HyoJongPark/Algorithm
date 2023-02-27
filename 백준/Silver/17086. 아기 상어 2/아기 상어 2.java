import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N, M;
    static int[][] board, check;
    static Queue<Point> q = new LinkedList<>();
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        check = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    q.offer(new Point(i, j, 0));
                    check[i][j] = Integer.MIN_VALUE;
                }
            }
        }

        System.out.println(BFS());
    }

    private static int BFS() {
        int result = 0;
        while (!q.isEmpty()) {
            Point shark = q.poll();

            for (int i = 0; i < 8; i++) {
                int nextX = shark.x + dx[i];
                int nextY = shark.y + dy[i];

                if (isValid(nextX, nextY)) {
                    q.offer(new Point(nextX, nextY, shark.count + 1));
                    check[nextX][nextY] = shark.count + 1;
                    result = shark.count + 1;
                }
            }
        }
        return result;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M &&
                check[nextX][nextY] == 0;
    }

    static class Point {
        int x, y;
        int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}