import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        int x = 0, y = 0;
        Queue<Point> fire = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'J') {
                    board[i][j] = '.';
                    x = i;
                    y = j;
                } else if (board[i][j] == 'F') {
                    fire.offer(new Point(i, j, 0));
                }
            }
        }

        int result = BFS(x, y, fire);
        if (result == -1) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(result);
        }
    }

    private static int BFS(int x, int y, Queue<Point> fire) {
        Queue<Point> q = new LinkedList<>();
        boolean[][] check = new boolean[N][M];

        q.offer(new Point(x, y, 0));
        check[x][y] = true;
        int currentCount = 0;
        while (!q.isEmpty()) {
            Point current = q.poll();
            if (currentCount == current.count) {
                currentCount += 1;
                spreadFire(fire);
            }

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];

                if (canRunAway(nextX, nextY)) {
                    return current.count + 1;
                } else if (board[nextX][nextY] == '.' && !check[nextX][nextY]) {
                    q.offer(new Point(nextX, nextY, current.count + 1));
                    check[nextX][nextY] = true;
                }
            }
        }
        return -1;
    }

    private static void spreadFire(Queue<Point> fire) {
        int size = fire.size();
        for (int i = 0; i < size; i++) {
            Point current = fire.poll();

            for (int j = 0; j < 4; j++) {
                int nextX = current.x + dx[j];
                int nextY = current.y + dy[j];

                if (!canRunAway(nextX, nextY) && board[nextX][nextY] == '.') {
                    board[nextX][nextY] = 'F';
                    fire.offer(new Point(nextX, nextY, 0));
                }
            }
        }
    }

    private static boolean canRunAway(int nextX, int nextY) {
        return nextX < 0 || nextX >= N || nextY < 0 || nextY >= M;
    }

    static class Point {
        int x, y;
        int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}