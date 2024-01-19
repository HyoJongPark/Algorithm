import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int N = 19;
    static int[][] board = new int[N][N];
    static int[][] d = {{1, 1}, {1, 0}, {0, 1}, {-1, 1}, {-1, -1}, {-1, 0}, {0, -1}, {1, -1}}; //+4 방향은 반대

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != 0) {
                    for (int direction = 0; direction < 4; direction++) {
                        //반대 방향이 몇칸 일치하는지 -> 볼필요 없이 이전 탐색했기 때문에 반대가 일치함에도 현재라면 탐색 불필요
                        if (!alreadyExplored(i, j, direction + 4)) {
                            boolean status = DFS(1, i, j, direction);

                            if (status) {
                                System.out.printf("%d\n%d %d", board[i][j], i + 1, j + 1);
                                System.exit(0);
                            }
                        }
                    }
                }
            }
        }

        System.out.println(0);
    }

    private static boolean alreadyExplored(int x, int y, int direction) {
        int beforeX = x + d[direction][0];
        int beforeY = y + d[direction][1];

        return isValid(beforeX, beforeY) && board[x][y] == board[beforeX][beforeY];
    }

    private static boolean DFS(int level, int x, int y, int direction) {
        boolean status = false;

        int nextX = x + d[direction][0];
        int nextY = y + d[direction][1];
        if (isValid(nextX, nextY) && board[nextX][nextY] == board[x][y]) {
            if (level == 5) return false;
            status = DFS(level + 1, nextX, nextY, direction);

            if (status) {
                return true;
            }
        } else if (level == 5) {
            return true;
        }

        return false;
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
