import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    static int N = 12, M = 6;
    static char[][] board = new char[N][M];
    static int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        boolean removed;
        int count = 0;
        do {
            removed = checkAndRemovePuyo();

            if (removed) {
                moveDown();
                count++;
            }
        } while (removed);
        System.out.println(count);
    }

    private static void moveDown() {
        for (int j = 0; j < M; j++) {
            int lowestEmptySpace = -1;

            for (int i = N - 1; i >= 0; i--) {
                if (lowestEmptySpace == -1 && board[i][j] == '.') {
                    lowestEmptySpace = i;
                } else if (lowestEmptySpace != -1 && board[i][j] != '.') {
                    board[lowestEmptySpace--][j] = board[i][j];
                    board[i][j] = '.';
                }
            }
        }
    }

    private static boolean checkAndRemovePuyo() {
        boolean removed = false;
        boolean[][] check = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != '.') {
                    Queue<int[]> rememberQueue = checkPuyo(i, j, check);

                    if (rememberQueue.size() >= 4) {
                        removePuyo(rememberQueue);
                        removed = true;
                    }
                }
            }
        }
        return removed;
    }

    private static Queue<int[]> checkPuyo(int startX, int startY, boolean[][] check) {
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> rememberQueue = new LinkedList<>();

        q.offer(new int[]{startX, startY});
        rememberQueue.offer(new int[]{startX, startY});
        check[startX][startY] = true;
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < d.length; i++) {
                int nextX = current[0] + d[i][0];
                int nextY = current[1] + d[i][1];

                if (isValid(nextX, nextY) && !check[nextX][nextY] && board[nextX][nextY] == board[startX][startY]) {
                    q.offer(new int[]{nextX, nextY});
                    rememberQueue.offer(new int[]{nextX, nextY});
                    check[nextX][nextY] = true;
                }
            }
        }
        return rememberQueue;
    }

    private static void removePuyo(Queue<int[]> rememberQueue) {
        while (!rememberQueue.isEmpty()) {
            int[] current = rememberQueue.poll();

            board[current[0]][current[1]] = '.';
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }
}
