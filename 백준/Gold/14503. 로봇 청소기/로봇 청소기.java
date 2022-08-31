import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Point {
    int x;
    int y;
    int direction;

    public Point(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}

class Main {
    static int N, M, count = 0;
    static int[][] board;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmp[0];
        M = tmp[1];
        board = new int[N][M];

        tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int x = tmp[0], y = tmp[1], direction = tmp[2];
        for (int i = 0; i < N; i++){
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        BFS(x, y, direction);
        System.out.println(count);
    }

    private static void BFS(int x, int y, int direction) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y, direction));

        while (!q.isEmpty()) {
            Point point = q.poll();
            if (board[point.x][point.y] == 0) {
                board[point.x][point.y] = 2;
                count++;
            }

            boolean doClean = false;
            for (int i = point.direction + 3; i >= point.direction; i--) {
                int next_x = point.x + dx[i % 4];
                int next_y = point.y + dy[i % 4];

                if (canClean(next_x, next_y)) {
                    q.offer(new Point(next_x, next_y, i % 4));
                    doClean = true;
                    break;
                }
            }

            if (!doClean) {
                int next_x = point.x - dx[point.direction];
                int next_y = point.y - dy[point.direction];

                if (canBack(next_x, next_y)) {
                    q.offer(new Point(next_x, next_y, point.direction));
                }
            }
        }
    }

    private static boolean canClean(int next_x, int next_y) {
        return 0 <= next_x && next_x < N && 0 <= next_y && next_y < M
                && board[next_x][next_y] != 1 && board[next_x][next_y] != 2;
    }

    private static boolean canBack(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M
                && board[x][y] != 1;
    }
}