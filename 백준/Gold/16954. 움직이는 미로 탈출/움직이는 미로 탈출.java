import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    static int N = 8;
    static char[][] board = new char[N][N];
    static Queue<int[]> walls = new LinkedList<>();
    static Queue<int[]> character = new LinkedList<>();
    static int[][] d = new int[][]{{0, 0}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();

            for (int j = 0; j < N; j++) {
                if (board[i][j] == '#') walls.offer(new int[]{i, j});
            }
        }

        if (walls.isEmpty()) {
            System.out.println(1);
            System.exit(0);
        }

        boolean result = play();
        if (result) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static boolean play() {
        boolean canEscape = false;
        character.offer(new int[]{N - 1, 0, -1});

        while (!canEscape && !character.isEmpty()) {
            canEscape = moveCharacter(character.size());
            moveBlock(walls.size());
        }
        return canEscape;
    }

    private static boolean moveCharacter(int size) {
        boolean[][] check = new boolean[N][N];
        for (int i = 0; i < size; i++) {
            int[] current = character.poll();

            if (board[current[0]][current[1]] != '.') continue;
            for (int j = 0; j < d.length; j++) {
                int nextX = current[0] + d[j][0];
                int nextY = current[1] + d[j][1];

                if (isValid(nextX, nextY) && board[nextX][nextY] == '.' && !check[nextX][nextY]) {
                    if (nextX == 0 && nextY == N - 1) return true;
                    if (nextX != 0 && board[nextX - 1][nextY] != '.') continue;

                    check[nextX][nextY] = true;
                    character.offer(new int[]{nextX, nextY});
                }
            }
        }
        return false;
    }

    private static void moveBlock(int size) {
        for (int i = 0; i < size; i++) {
            int[] current = walls.poll();
            if (board[current[0]][current[1]] == '2') board[current[0]][current[1]] = '#';
            else board[current[0]][current[1]] = '.';

            int nextX = current[0] + 1;
            if (isValid(nextX, current[1])) {
                if (board[nextX][current[1]] == '#') board[nextX][current[1]] = '2';
                else board[nextX][current[1]] = '#';
                walls.offer(new int[]{nextX, current[1]});
            }
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < N;
    }
}
