import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int start, target;
    static boolean[] check = new boolean[1000000001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        System.out.println(BFS());
    }

    private static int BFS() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(start, 1));
        check[start] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();
            long[] nextX = {point.x + point.x, point.x * 10 + 1};

            for (int i = 0; i < nextX.length; i++) {
                if (nextX[i] == target) return point.distance + 1;
                else if (isValid(nextX[i])) {
                    check[(int) nextX[i]] = true;
                    q.offer(new Point(nextX[i], point.distance + 1));
                }

            }
        }
        return -1;
    }

    private static boolean isValid(long nextX) {
        return 1 <= nextX && nextX < check.length && !check[(int) nextX];
    }

    static class Point {
        long x;
        int distance;

        public Point(long x, int distance) {
            this.x = x;
            this.distance = distance;
        }
    }
}