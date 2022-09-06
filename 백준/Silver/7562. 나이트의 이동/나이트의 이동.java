import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

class Point {
    int x;
    int y;
    int count;

    public Point(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}

class Main {
    static int T, N;
    static Point start, target;
    static boolean[][] check;
    static int[] dx = {-1, -1, -2, -2, 1, 1, 2, 2};
    static int[] dy = {-2, 2, -1, 1, -2, 2, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            check = new boolean[N][N];
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            start = new Point(tmp[0], tmp[1], 0);
            tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            target = new Point(tmp[0], tmp[1], 0);

            if (start.x == target.x && start.y == target.y) {
                System.out.println(0);
            } else {
                Point bfs = BFS();
                System.out.println(Objects.requireNonNull(bfs).count);
            }
        }
    }

    private static Point BFS() {
        Queue<Point> q = new LinkedList<>();
        q.offer(start);
        check[start.x][start.y] = true;

        int next_x, next_y;
        while (!q.isEmpty()) {
            Point point = q.poll();

            for (int i = 0; i < 8; i++) {
                next_x = point.x + dx[i];
                next_y = point.y + dy[i];
                if (next_x == target.x && next_y == target.y){
                    return new Point(next_x, next_y, point.count + 1);
                }

                if (isValid(next_x, next_y)) {
                    q.offer(new Point(next_x, next_y, point.count + 1));
                    check[next_x][next_y] = true;
                }
            }
        }

        return null;
    }

    private static boolean isValid(int next_x, int next_y) {
        return 0 <= next_x && next_x < N && 0 <= next_y && next_y < N
                && !check[next_x][next_y];
    }
}