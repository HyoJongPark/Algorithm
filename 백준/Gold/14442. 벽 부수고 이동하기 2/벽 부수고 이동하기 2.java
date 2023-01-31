import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int n, m, k;
    static char[][] board;
    static int[][] check;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        check = new int[n][m];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
            Arrays.fill(check[i], Integer.MAX_VALUE);
        }
        
        System.out.println(BFS());
    }

    private static int BFS() {
        if (n == 1 && m == 1) {
            return 1;
        }
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, 1, 0));
        check[0][0] = 0;
        while (!q.isEmpty()) {
            Point current = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];

                if (nextX == n - 1 && nextY == m - 1) return current.distance + 1;
                if (isValid(nextX, nextY)) {
                    if (board[nextX][nextY] == '1' && current.count < k && check[nextX][nextY] > current.count + 1) {
                        q.offer(new Point(nextX, nextY, current.distance + 1, current.count + 1));
                        check[nextX][nextY] = current.count + 1;
                    } else if (board[nextX][nextY] != '1' && check[nextX][nextY] > current.count) {
                        q.offer(new Point(nextX, nextY, current.distance + 1, current.count));
                        check[nextX][nextY] = current.count;
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < n && 0 <= nextY && nextY < m;
    }

    static class Point {
        int x, y;
        int distance;
        int count;

        public Point(int x, int y, int distance, int count) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.count = count;
        }
    }
}