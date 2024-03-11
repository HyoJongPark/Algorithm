import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[][] board;
    static int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        int cheeseCount = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 1) {
                    cheeseCount++;
                }
            }
        }

        int dayCount = 1;
        StringBuilder sb = new StringBuilder();
        while (cheeseCount != 0) {
            initializeOuterAir();
            int meltCheeseCount = melt();

            if (meltCheeseCount == cheeseCount) {
                sb.append(dayCount).append("\n").append(meltCheeseCount);
                break;
            }
            cheeseCount -= meltCheeseCount;
            dayCount++;
        }
        System.out.println(sb);
    }

    private static void initializeOuterAir() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] check = new boolean[N][M];

        q.offer(new int[]{0, 0});
        check[0][0] = true;
        board[0][0] = 2;
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < d.length; i++) {
                int nextX = current[0] + d[i][0];
                int nextY = current[1] + d[i][1];

                if (isValid(nextX, nextY) && !check[nextX][nextY] && board[nextX][nextY] != 1) {
                    if (board[nextX][nextY] == 0) {
                        board[nextX][nextY] = 2;
                    }
                    q.offer(new int[]{nextX, nextY});
                    check[nextX][nextY] = true;
                }
            }
        }
    }

    //바깥 쪽 공기를 모두 -1로 채우기
    private static int melt() {
        int[][] newBoard = new int[N][M];
        int meltCount = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                newBoard[i][j] = board[i][j];
                if (board[i][j] == 1 && canMelt(i, j)) {
                    meltCount++;
                    newBoard[i][j] = 2;
                }
            }
        }
        board = newBoard;
        return meltCount;
    }

    private static boolean canMelt(int x, int y) {
        for (int i = 0; i < d.length; i++) {
            int nextX = x + d[i][0];
            int nextY = y + d[i][1];

            if (isValid(nextX, nextY) && board[nextX][nextY] == 2) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }
}
