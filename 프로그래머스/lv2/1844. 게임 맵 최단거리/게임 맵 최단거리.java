import java.util.LinkedList;
import java.util.Queue;

class Point {
    int x;
    int y;
    int distance;

    public Point(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }
}

class Solution {
    static int N, M;
    static int[][] maps;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;
        Solution.maps = maps;

        Queue<Point> q = new LinkedList<>();
        if (maps[0][0] == 1) {
            q.offer(new Point(0, 0, 1));
            maps[0][0] = 0;
        }

        while (!q.isEmpty()) {
            Point point = q.poll();

            for (int i = 0; i < 4; i++) {
                int next_x = point.x + dx[i];
                int next_y = point.y + dy[i];

                if (next_x == N - 1 && next_y == M - 1) return point.distance + 1;
                else if (isValid(next_x, next_y)) {
                    maps[next_x][next_y] = 0;
                    q.offer(new Point(next_x, next_y, point.distance + 1));
                }
            }
        }

        return -1;
    }

    private boolean isValid(int next_x, int next_y) {
        return 0 <= next_x && next_x < N && 0 <= next_y && next_y < M
                && maps[next_x][next_y] == 1;
    }
}