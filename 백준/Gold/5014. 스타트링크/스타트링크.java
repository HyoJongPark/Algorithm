import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, current, target, up, down;
    static boolean[] check;
    static int[] dy;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        current = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        up = Integer.parseInt(st.nextToken());
        down = Integer.parseInt(st.nextToken());
        check = new boolean[N + 1];
        dy = new int[]{up, - down};

        int bfs = current == target ? 0 : BFS();
        System.out.println(bfs == -1 ? "use the stairs" : bfs);
    }

    private static int BFS() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(current, 0));
        check[current] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();

            for (int i = 0; i < 2; i++) {
                int nextY = point.y + dy[i];
                if (nextY == target) return point.distance + 1;
                if (isValid(nextY)) {
                    q.offer(new Point(nextY, point.distance + 1));
                    check[nextY] = true;
                }
            }
        }

        return -1;
    }

    private static boolean isValid(int nextY) {
        return 1 <= nextY && nextY < check.length && !check[nextY];
    }

    static class Point {
        int y;
        int distance;

        public Point(int y, int distance) {
            this.y = y;
            this.distance = distance;
        }
    }
}