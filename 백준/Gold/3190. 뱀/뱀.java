import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, K, L;
    static int[][] board;
    static Queue<Route> routes = new LinkedList<>();
    static Queue<Point> snake = new LinkedList<>();

    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];
        for (; K > 0; K--) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }
        L = Integer.parseInt(br.readLine());
        for (; L > 0; L--) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            routes.offer(new Route(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
        }

        snake.offer(new Point(1, 1));
        board[1][1] = 2;
        DFS(0, new Point(1, 1), 0, routes.poll());
    }

    private static void DFS(int level, Point point, int direction, Route nextRoute) {
        if (nextRoute != null && level == nextRoute.time) {
            direction = (dx.length + direction + nextRoute.changeDirection()) % dx.length;
            if (routes.isEmpty()) {
                nextRoute = null;
            } else {
                nextRoute = routes.poll();
            }
        }

        int nextX = point.x + dx[direction];
        int nextY = point.y + dy[direction];
        if (isValid(nextX, nextY)) {
            if (board[nextX][nextY] != 1) {
                Point lastSnakeBody = snake.poll();
                board[lastSnakeBody.x][lastSnakeBody.y] = 0;
            }

            snake.offer(new Point(nextX, nextY));
            board[nextX][nextY] = 2;
            DFS(level + 1, new Point(nextX, nextY), direction, nextRoute);
        } else {
            System.out.println(level + 1);
            System.exit(0);
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 1 <= nextX && nextX <= N && 1 <= nextY && nextY <= N
                && board[nextX][nextY] != 2;
    }

    static class Route {
        int time;
        char direction;

        public Route(int time, char direction) {
            this.time = time;
            this.direction = direction;
        }

        public int changeDirection() {
            return this.direction == 'D' ? 1 : -1;
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
