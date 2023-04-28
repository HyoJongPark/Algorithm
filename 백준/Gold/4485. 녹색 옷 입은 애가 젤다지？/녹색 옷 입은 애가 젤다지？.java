import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class Main {
    static int N;
    static int[][] board, distance;
    static int[][] d = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int cnt = 1;
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            board = new int[N][N];
            distance = new int[N][N];

            for (int i = 0; i < N; i++) {
                board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                Arrays.fill(distance[i], Integer.MAX_VALUE);
            }

            sb.append(String.format("Problem %d: %d\n", cnt++, dijkstra()));
        }
        System.out.println(sb);
    }

    private static int dijkstra() {
        PriorityQueue<Point> pq = new PriorityQueue<>();

        distance[0][0] = board[0][0];
        pq.offer(new Point(0, 0, board[0][0]));
        while (!pq.isEmpty()) {
            Point current = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + d[i][0];
                int nextY = current.y + d[i][1];

                if (isValid(nextX, nextY) && distance[nextX][nextY] > board[nextX][nextY] + distance[current.x][current.y]) {
                    distance[nextX][nextY] = board[nextX][nextY] + distance[current.x][current.y];
                    pq.offer(new Point(nextX, nextY, distance[nextX][nextY]));
                }
            }
        }

        return distance[N - 1][N - 1];
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < N;
    }

    static class Point implements Comparable<Point> {
        int x, y;
        int rupee;

        public Point(int x, int y, int rupee) {
            this.x = x;
            this.y = y;
            this.rupee = rupee;
        }

        @Override
        public int compareTo(Point o) {
            return this.rupee - o.rupee;
        }
    }
}