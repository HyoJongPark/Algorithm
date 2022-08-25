import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Point {
    int x;
    int y;
    int color;

    public Point(int x, int y, int color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }
}

class Solution {

    static int N;
    static int M;
    static boolean[][] check;
    static int[][] picture;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int[] solution(int n, int m, int[][] picture) {
        check = new boolean[n][m];
        this.picture = picture;
        N = n;
        M = m;
        int numberOfArea = 0;
        int maxSizeOfOneArea = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (picture[i][j] != 0 && !check[i][j]) {
                    maxSizeOfOneArea = Math.max(BFS(i, j), maxSizeOfOneArea);
                    numberOfArea++;
                }
            }
        }
        return new int[]{numberOfArea, maxSizeOfOneArea};
    }

    private int BFS(int x, int y) {
        int cnt = 1;
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y, picture[x][y]));
        check[x][y] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();

            for (int i = 0; i < 4; i++) {
                int next_x = point.x + dx[i];
                int next_y = point.y + dy[i];

                if (isValid(next_x, next_y, point.color)) {
                    q.offer(new Point(next_x, next_y, point.color));
                    check[next_x][next_y] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private boolean isValid(int next_x, int next_y, int color) {
        return 0 <= next_x && next_x < N && 0 <= next_y && next_y < M && color == picture[next_x][next_y]
                && !check[next_x][next_y];
    }
}