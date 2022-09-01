import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;
    int distance;

    public Point(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}

class Main {
    static int N, fishCount = 0, size = 2, eatCount = 0;
    static Point shirk = null;
    static int[][] board;
    static boolean[][] check;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 9) {
                    shirk = new Point(i, j, 0);
                    board[i][j] = 0;
                } else {
                    fishCount++;
                    board[i][j] = tmp;
                }
            }
        }

        while (fishCount != 0) {
            int beforeEat = fishCount;
            BFS();

            if (eatCount == size) {
                eatCount = 0;
                size++;
            }
            if (beforeEat == fishCount) break;
        }

        System.out.println(shirk.distance);
    }

    private static void BFS() {
        check = new boolean[N][N];
        Point victim = null;
        Queue<Point> q = new LinkedList<>();
        q.offer(shirk);
        check[shirk.x][shirk.y] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();
            if (victim != null && victim.distance == point.distance) break;

            for (int i = 0; i < 4; i++) {
                int next_x = point.x + dx[i];
                int next_y = point.y + dy[i];

                if (isValid(next_x, next_y)) {
                    if (canEat(next_x, next_y)) {
                        if (victim == null) {
                            victim = new Point(next_x, next_y, point.distance + 1);
                        } else {
                            if (victim.x > next_x) victim = new Point(next_x, next_y, point.distance + 1);
                            else if (victim.x == next_x && victim.y > next_y) victim = new Point(next_x, next_y, point.distance + 1);
                        }
                    }

                    check[next_x][next_y] = true;
                    q.offer(new Point(next_x, next_y, point.distance + 1));
                }
            }
        }

        if (victim != null) {
            shirk = victim;
            fishCount--;
            eatCount++;
            board[victim.x][victim.y] = 0;
        }
    }

    private static boolean isValid(int next_x, int next_y) {
        return 0 <= next_x && next_x < N && 0 <= next_y && next_y < N
                && !check[next_x][next_y] && board[next_x][next_y] <= size;
    }

    private static boolean canEat(int next_x, int next_y) {
        return board[next_x][next_y] != 0 && board[next_x][next_y] < size;
    }
}