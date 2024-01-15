import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    static int N = 8;
    static char[][] board = new char[N][N];
    static int[][] d = new int[][]{{0, 0}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        boolean result = play();
        if (result) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static boolean play() {
        Queue<int[]> character = new LinkedList<>();

        character.offer(new int[]{N - 1, 0});
        boolean canEscape = false;

        while (!canEscape && !character.isEmpty()) {
            canEscape = moveCharacter(character);
            moveBlock();
        }
        return canEscape;
    }

    private static boolean moveCharacter(Queue<int[]> character) {
        boolean[][] check = new boolean[N][N];
        int size = character.size();

        for (int i = 0; i < size; i++) {
            int[] current = character.poll();

            if (board[current[0]][current[1]] != '.') continue;
            for (int j = 0; j < d.length; j++) {
                int nextX = current[0] + d[j][0];
                int nextY = current[1] + d[j][1];

                if (isValid(nextX, nextY) && board[nextX][nextY] == '.' && !check[nextX][nextY]) {
                    if (nextX == 0 && nextY == N - 1) return true;

                    check[nextX][nextY] = true;
                    character.offer(new int[]{nextX, nextY});
                }
            }
        }
        return false;
    }

    private static void moveBlock() {
        for (int i = 6; i >= 0; i--) {
            for (int j = 0; j < N; j++) {
                board[i + 1][j] = board[i][j];
            }
        }
        for (int i = 0; i < 8; i++) {
            board[0][i] = '.';
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < N;
    }
}
