import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] board;
    static boolean[][] check;
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        check = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1 && !check[i][j]) {
                    BFS(i, j);
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    private static void BFS(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        check[x][y] = true;

        while (!q.isEmpty()) {
            Point current = q.poll();

            for (int i = 0; i < 8; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];

                if (isValid(nextX, nextY) && !check[nextX][nextY]) {
                    q.offer(new Point(nextX, nextY));
                    check[nextX][nextY] = true;
                }
            }
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0<= nextY && nextY < M && board[nextX][nextY] == 1;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}