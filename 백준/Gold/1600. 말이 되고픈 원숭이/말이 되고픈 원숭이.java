import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;
    int count;
    int distance;

    public Point(int x, int y, int count, int distance) {
        this.x = x;
        this.y = y;
        this.count = count;
        this.distance = distance;
    }
}

class Main {
    static int K, W, H;
    static int[][] board;
    static boolean[][][] check;
    static int[] dx = {-1, 1, 0, 0, -2, -1, 1, 2, 1, 2, -1, -2};
    static int[] dy = {0, 0, -1, 1, -1, -2, 2, 1, -2, -1, 2, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        board = new int[H][W];
        check = new boolean[H][W][K + 1];
        for (int i = 0; i < H; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        
        System.out.println(BFS());
    }

    private static int BFS() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, 0, 0));
        check[0][0][0] = true;
        if (W == 1 && H == 1 && board[0][0] == 0) return 0;

        while (!q.isEmpty()) {
            Point point = q.poll();

            int maxIndex = point.count >= K ? 4 : dx.length;
            for (int i = 0; i < maxIndex; i++) {
                int nextX = point.x + dx[i];
                int nextY = point.y + dy[i];
                int nextCount = i >= 4 ? point.count + 1 : point.count;

                if (nextX == H - 1 && nextY == W - 1) return point.distance + 1;
                if (isValid(nextX, nextY, nextCount)) {
                    q.offer(new Point(nextX, nextY, nextCount, point.distance + 1));
                    check[nextX][nextY][nextCount] = true;
                }
            }
        }

        return -1;
    }

    private static boolean isValid(int nextX, int nextY, int nextCount) {
        return 0 <= nextX && nextX < H && 0 <= nextY && nextY < W
                && board[nextX][nextY] != 1 && !check[nextX][nextY][nextCount];
    }
}