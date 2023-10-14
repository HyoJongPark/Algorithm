import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N;
    static int[][] board;
    static int[][] checkIsland;

    static final int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        checkIsland = new int[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int islandNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1 && checkIsland[i][j] == 0) {
                    checkIsland(i, j, islandNum++);
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 1 && checkIsland[i][j] != 0) {
                    result = Math.min(makeBridge(i, j), result);
                }
            }
        }
        System.out.println(result);
    }

    private static void checkIsland(int x, int y, int islandNum) {
        Queue<int[]> q = new LinkedList<>();

        checkIsland[x][y] = islandNum;
        q.offer(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current[0] + d[i][0];
                int nextY = current[1] + d[i][1];

                if (isValid(nextX, nextY) && board[nextX][nextY] == 1 && checkIsland[nextX][nextY] == 0) {
                    checkIsland[nextX][nextY] = islandNum;
                    q.offer(new int[]{nextX, nextY});
                }
            }
        }
    }

    private static int makeBridge(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] check = new boolean[N][N];

        int currentIslandNum = checkIsland[x][y];
        check[x][y] = true;
        q.offer(new int[]{x, y, 0});
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current[0] + d[i][0];
                int nextY = current[1] + d[i][1];

                if (isValid(nextX, nextY)) {
                    if (board[nextX][nextY] == 0 && !check[nextX][nextY]) {
                        check[nextX][nextY] = true;
                        q.offer(new int[]{nextX, nextY, current[2] + 1});
                    } else if (board[nextX][nextY] == 1 && checkIsland[nextX][nextY] != currentIslandNum) {
                        return current[2];
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < N;
    }
}