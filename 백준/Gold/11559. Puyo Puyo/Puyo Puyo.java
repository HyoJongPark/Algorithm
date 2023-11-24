import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {

    static final int N = 12, M = 6;
    static final int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    static char[][] board = new char[N][M];
    static boolean[][] check;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        int count = 0;
        boolean status = true;
        while (status) {
            status = findAndRemoveEqualColor();

            if (status) {
                moveTile();
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean findAndRemoveEqualColor() {
        boolean status = false;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != '.') {
                    int count = findEqualColor(i, j, board[i][j]);

                    if (count >= 4) {
                        removeEqualColor(i, j);
                        status = true;
                    }
                }
            }
        }
        return status;
    }

    private static void moveTile() {
        for (int j = 0; j < M; j++) {
            int lowestEmptySpace = -1;

            for (int i = 11; i >= 0; i--) {
                if (lowestEmptySpace == -1 && board[i][j] == '.') {
                    lowestEmptySpace = i;
                } else if (lowestEmptySpace != -1 && board[i][j] != '.') {
                    board[lowestEmptySpace--][j] = board[i][j];
                    board[i][j] = '.';
                }
            }
        }
    }

    private static int findEqualColor(int x, int y, char color) {
        Queue<int[]> q = new LinkedList<>();
        check = new boolean[N][M];
        int count = 1;

        q.offer(new int[]{x, y});
        check[x][y] = true;
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < d.length; i++) {
                int nextX = current[0] + d[i][0];
                int nextY = current[1] + d[i][1];

                if (isValid(nextX, nextY) && board[nextX][nextY] == color && !check[nextX][nextY]) {
                    count++;
                    check[nextX][nextY] = true;
                    q.offer(new int[]{nextX, nextY});
                }
            }
        }
        return count;
    }

    private static void removeEqualColor(int x, int y) {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < d.length; i++) {
                int nextX = current[0] + d[i][0];
                int nextY = current[1] + d[i][1];

                if (isValid(nextX, nextY) && board[nextX][nextY] != '.' && check[nextX][nextY]) {
                    board[nextX][nextY] = '.';
                    q.offer(new int[]{nextX, nextY});
                }
            }
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }
}
