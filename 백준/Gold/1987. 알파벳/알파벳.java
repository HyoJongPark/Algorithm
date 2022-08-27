import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;
    int cnt;
    String alpha;

    public Point(int x, int y, int cnt, String alpha) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.alpha = alpha;
    }
}

class Main {
    static int N;
    static int M;
    static char[][] board;
    static boolean[][] check_board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);
        board = new char[N][M];
        check_board = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        check_board[0][0] = true;
        System.out.println(DFS(1, 0, 0, board[0][0] + ""));
    }

    private static int DFS(int level, int x, int y, String alpha) {
        int max = level;

        for (int i = 0; i < 4; i++) {
            int next_x = x + dx[i];
            int next_y = y + dy[i];

            if (isValid(next_x, next_y, alpha)) {
                check_board[next_x][next_y] = true;
                max = Math.max(DFS(level + 1, next_x, next_y, alpha + board[next_x][next_y]), max);
                check_board[next_x][next_y] = false;
            }
        }
        return max;
    }

    private static boolean isValid(int next_x, int next_y, String alpha) {
        return 0 <= next_x && next_x < N && 0 <= next_y && next_y < M
                && !check_board[next_x][next_y] && !alpha.contains(board[next_x][next_y]+"");
    }
}