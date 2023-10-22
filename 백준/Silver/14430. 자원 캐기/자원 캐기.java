import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[][] board, check;
    static int[][] d = {{0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        check = new int[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(check[i], -1);
        }

        BFS();
        System.out.println(check[N - 1][M - 1]);
    }

    private static void BFS() {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0, 0, board[0][0] == 1 ? 1 : 0});
        check[0][0] = board[0][0] == 1 ? 1 : 0;
        while (!q.isEmpty()) {
            int[] current = q.poll();

            if (check[current[0]][current[1]] > current[2]) continue;

            for (int i = 0; i < 2; i++) {
                int nextX = current[0] + d[i][0];
                int nextY = current[1] + d[i][1];
                int nextVal;

                if (isValid(nextX, nextY) &&
                        check[nextX][nextY] < (nextVal = current[2] + (board[nextX][nextY] == 1 ? 1 : 0))) {
                    q.offer(new int[]{nextX, nextY, nextVal});
                    check[nextX][nextY] = nextVal;
                }
            }
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }
}
