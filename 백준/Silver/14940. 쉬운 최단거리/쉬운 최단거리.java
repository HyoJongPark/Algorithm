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

    public Point(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}

class Main {
    static int N, M, x, y;
    static int[][] board;
    static int[][] check;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        check = new int[N][M];
        for (int i = 0; i < N; i++) Arrays.fill(check[i], -1);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 2) {
                    x = i;
                    y = j;
                    check[i][j] = 0;
                } else if (board[i][j] == 0) check[i][j] = 0;
            }
        }

        int[][] bfs = BFS(x, y);
        StringBuilder sb = new StringBuilder();
        for (int[] bf : bfs) {
            for (int i : bf) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static int[][] BFS(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y, 0));
        check[x][y] = 0;

        while (!q.isEmpty()) {
            Point point = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = point.x + dx[i];
                int nextY = point.y + dy[i];

                if (isValid(nextX, nextY) && check[nextX][nextY] == -1) {
                    check[nextX][nextY] = point.count + 1;
                    q.offer(new Point(nextX, nextY, point.count + 1));
                }
            }
        }

        return check;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M && board[nextX][nextY] != 0;
    }
}