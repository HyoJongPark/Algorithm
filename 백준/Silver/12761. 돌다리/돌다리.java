import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x;
    int distance;

    public Point(int x, int distance) {
        this.x = x;
        this.distance = distance;
    }
}

public class Main {
    static int A, B, startPosition, targetPosition;
    static int[] dx;

    static boolean[] check = new boolean[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        startPosition = Integer.parseInt(st.nextToken());
        targetPosition = Integer.parseInt(st.nextToken());

        dx = new int[]{1, -1, A, -A, B, -B};
        BFS();
    }

    private static void BFS() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(startPosition, 0));
        check[startPosition] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();

            int nextDistance = point.distance + 1;
            for (int i = 0; i < 6; i++) {
                int nextX = point.x + dx[i];

                if (isValid(nextX)) {
                    q.offer(new Point(nextX, nextDistance));
                    check[nextX] = true;
                }
            }
            if (isValid(point.x * A)) {
                q.offer(new Point(point.x * A, nextDistance));
                check[point.x * A] = true;
            }
            if (isValid(point.x * B)) {
                q.offer(new Point(point.x * B, nextDistance));
                check[point.x * B] = true;
            }
            if (check[targetPosition]) {
                System.out.println(nextDistance);
                System.exit(0);
            }
        }
    }

    private static boolean isValid(int x) {
        return 0 <= x && x < 100001 && !check[x];
    }
}