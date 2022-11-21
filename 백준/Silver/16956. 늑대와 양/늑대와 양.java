import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int R, C;
    static char[][] board;
    static boolean[][] check;
    static Queue<Point> q = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        check = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < C; j++) {
                board[i][j] = tmp.charAt(j);
                if (board[i][j] == 'W') {
                    q.add(new Point(i, j));
                    check[i][j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (BFS()) {
            sb.append("1").append("\n");
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    sb.append(board[i][j]);
                }
                sb.append("\n");
            }
        } else {
            sb.append("0");
        }
        System.out.println(sb);
    }

    static boolean BFS() {
        while (!q.isEmpty()) {
            Point point = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = point.x + dx[i];
                int nextY = point.y + dy[i];
                if (isValid(nextX, nextY)) {
                    if (board[nextX][nextY] == '.') {
                        board[nextX][nextY] = 'D';
                    }
                    if (board[nextX][nextY] == 'S') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean isValid(int nextX, int nextY) {
        return  0 <= nextX && nextX < R && 0 <= nextY && nextY < C
                && !check[nextX][nextY];
    }
}