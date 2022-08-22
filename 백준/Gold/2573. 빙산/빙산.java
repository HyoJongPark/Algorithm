import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main {
    static int N;
    static int M;
    static int numberOfIsland = Integer.MIN_VALUE;
    static int[][] board;
    static int[][] beforeMelting;
    static boolean[][] check;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmp[0];
        M = tmp[1];

        board = new int[N][M];
        beforeMelting = new int[N][M];
        for (int i = 0; i < N; i++) {
            tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            board[i] = tmp;
        }

        int cnt = 0;
        while (true) {
            check = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                System.arraycopy(board[i], 0, beforeMelting[i], 0, board[i].length);
            }
            numberOfIsland = 0;
            findIsland();
            if (numberOfIsland == 0) {
                cnt = 0;
                break;
            } else if (numberOfIsland >= 2) break;
            else cnt++;
            oneYearLater();
        }
        System.out.println(cnt);
    }

    private static void oneYearLater() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (beforeMelting[x][y] != 0) {
                    meltTheIce(x, y);
                }
            }
        }
    }

    private static void meltTheIce(int x, int y) {
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int next_x = x + dx[i];
            int next_y = y + dy[i];

            if (isValidPosition(next_x, next_y) && isIce(next_x, next_y)) cnt++;
        }

        board[x][y] -= cnt;
        if (board[x][y] < 0) board[x][y] = 0;
    }

    private static void findIsland() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (board[x][y] != 0 && !check[x][y]) {
                    numberOfIsland++;
                    BFS(new Point(x, y));
                }
            }
        }
    }

    private static void BFS(Point firstPoint) {
        Queue<Point> q = new LinkedList<>();
        q.offer(firstPoint);

        while (!q.isEmpty()) {
            Point point = q.poll();

            for (int i = 0; i < 4; i++) {
                int next_x = point.x + dx[i];
                int next_y = point.y + dy[i];

                if (isValid(next_x, next_y)) {
                    check[next_x][next_y] = true;
                    q.offer(new Point(next_x, next_y));
                }
            }
        }
    }

    private static boolean isValid(int next_x, int next_y) {
        return isValidPosition(next_x, next_y) && isLand(next_x, next_y);
    }

    private static boolean isValidPosition(int next_x, int next_y) {
        return 0 <= next_x && next_x < N && 0 <= next_y && next_y < M;
    }

    private static boolean isLand(int next_x, int next_y) {
        return !check[next_x][next_y] && board[next_x][next_y] != 0;
    }

    private static boolean isIce(int next_x, int next_y) {
        return beforeMelting[next_x][next_y] == 0;
    }
}