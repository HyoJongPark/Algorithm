import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Math.max;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main {
    static int N, M, result = Integer.MIN_VALUE;
    static int[][] board_beforeExtend;
    static Queue<Point> q = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board_beforeExtend = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board_beforeExtend[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0);
        System.out.println(result);
    }

    private static void DFS(int level) {
        if (level == 3) {
            BFS();
            return;
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (board_beforeExtend[x][y] == 0) {
                    board_beforeExtend[x][y] = 1;
                    DFS(level + 1);
                    board_beforeExtend[x][y] = 0;
                }
            }
        }
    }

    private static void BFS() {
        q.clear();
        int[][] copyBoard = copyBoard();

        while (!q.isEmpty()) {
            Point point = q.poll();

            for (int i = 0; i < 4; i++) {
                int next_x = point.x + dx[i];
                int next_y = point.y + dy[i];
                if (isValid(next_x, next_y, copyBoard)) {
                    copyBoard[next_x][next_y] = 2;
                    q.offer(new Point(next_x, next_y));
                }
            }
        }

        countSafeArea(copyBoard);
    }

    private static int[][] copyBoard() {
        int[][] copyBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyBoard[i][j] = board_beforeExtend[i][j];
                if (copyBoard[i][j] == 2) q.offer(new Point(i, j));
            }
        }
        return copyBoard;
    }

    private static boolean isValid(int next_x, int next_y, int[][] copyBoard) {
        return 0 <= next_x && next_x < N && 0 <= next_y && next_y < M
                && copyBoard[next_x][next_y] == 0;
    }

    private static void countSafeArea(int[][] copyBoard) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyBoard[i][j] == 0) count++;
            }
        }

        result = max(result, count);
    }
}