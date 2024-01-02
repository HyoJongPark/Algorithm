import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static char[][] board;

    static final int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
        int targetX = Integer.parseInt(st.nextToken()), targetY = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        System.out.println(BFS(x - 1, y - 1));
    }

    private static int BFS(int x, int y) {
        PriorityQueue<Point> q = new PriorityQueue<>();
        boolean[][] check = new boolean[N][M];

        q.offer(new Point(x, y, 0));
        check[x][y] = true;
        while (!q.isEmpty()) {
            Point current = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + d[i][0];
                int nextY = current.y + d[i][1];

                if (isValid(nextX, nextY) && !check[nextX][nextY]) {
                    check[nextX][nextY] = true;
                    if (board[nextX][nextY] == '#') return current.count + 1;
                    else if (board[nextX][nextY] == '1') q.offer(new Point(nextX, nextY, current.count + 1));
                    else q.offer(new Point(nextX, nextY, current.count));
                }
            }
        }
        return -1;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }

    static class Point implements Comparable<Point> {
        int x, y;
        int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Point o) {
            return this.count - o.count;
        }
    }
}
