import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Point {
    int x;
    int distance;

    public Point(int x, int distance) {
        this.x = x;
        this.distance = distance;
    }
}

class Main {
    static int N;
    static int[] snakeOrLadder = new int[101];
    static boolean[] check = new boolean[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmp[0] + tmp[1];
        for (int i = 0; i < N; i++) {
            tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            snakeOrLadder[tmp[0]] = tmp[1];
        }

        System.out.println(BFS());
    }

    private static int BFS() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(1, 0));
        check[1] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();
            for (int i = 6; i >= 1; i--) {
                int next_x = point.x + i;
                if (next_x >= 100) {
                    return point.distance + 1;
                }

                if (!check[next_x]) {
                    check[next_x] = true;
                    if (snakeOrLadder[next_x] != 0) {
                        next_x = snakeOrLadder[next_x];
                        check[next_x] = true;

                    }
                    q.offer(new Point(next_x, point.distance + 1));
                }
            }
        }

        return 404;
    }
}