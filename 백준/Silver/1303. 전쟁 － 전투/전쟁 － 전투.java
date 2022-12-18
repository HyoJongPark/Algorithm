import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M, ourCombatCapability, enemyCombatCapability;
    static char[][] board;
    static boolean[][] check;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[M][N];
        check = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            board[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!check[i][j]) {
                    if (board[i][j] == 'W') {
                        ourCombatCapability += BFS(i, j, 'W');
                    } else {
                        enemyCombatCapability += BFS(i, j, 'B');
                    }
                }
            }
        }

        System.out.printf("%d %d", ourCombatCapability, enemyCombatCapability);
    }

    private static int BFS(int x, int y, int color) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        check[x][y] = true;
        int cnt = 1;
        while (!q.isEmpty()) {
            Point point = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = point.x + dx[i];
                int nextY = point.y + dy[i];

                if (isValid(nextX, nextY, color)) {
                    q.offer(new Point(nextX, nextY));
                    check[nextX][nextY] = true;
                    cnt++;
                }
            }
        }
        return (int) Math.pow(cnt, 2);
    }

    private static boolean isValid(int nextX, int nextY, int color) {
        return 0 <= nextX && nextX < M && 0 <= nextY && nextY < N
                && board[nextX][nextY] == color && !check[nextX][nextY] ;
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