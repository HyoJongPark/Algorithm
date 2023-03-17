import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] nums, dp;
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N][M];
        dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
            nums[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        BFS();
        System.out.println(dp[N - 1][M - 1]);
    }

    private static void BFS() {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0));
        dp[0][0] = nums[0][0];

        while (!q.isEmpty()) {
            Point current = q.poll();

            for (int i = 0; i < 2; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];

                if (isValid(nextX, nextY, dp[current.x][current.y])) {
                    dp[nextX][nextY] = dp[current.x][current.y] + nums[nextX][nextY];
                    q.offer(new Point(nextX, nextY));
                }
            }
        }
    }

    private static boolean isValid(int nextX, int nextY, int i) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M && dp[nextX][nextY] < i + nums[nextX][nextY];
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}