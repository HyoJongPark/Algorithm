import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int N, M, D, result = 0, enemyCount = 0;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 1) {
                    enemyCount++;
                }
            }
        }

        dfs(0, new int[3]);
        System.out.println(result);
    }

    private static void dfs(int depth, int[] archer) {
        if (depth == 3) {
            result = Math.max(result, playGame(archer));
            return;
        }

        int beforePos = depth == 0 ? -1 : archer[depth - 1];
        for (int i = beforePos + 1; i < M; i++) {
            archer[depth] = i;
            dfs(depth + 1, archer);
        }
    }


    private static int playGame(int[] archer) {
        int[][] board = copyBoard();
        int currentEnemy = enemyCount, killedEnemies = 0;

        while (currentEnemy != 0) {
            int killCount = attackEnemy(archer, board);

            killedEnemies += killCount;
            currentEnemy -= killCount + moveEnemy(board);
        }
        return killedEnemies;
    }

    private static int attackEnemy(int[] archer, int[][] board) {
        int[][] killedEnemies = new int[3][2];

        for (int i = 0; i < archer.length; i++) {
            killedEnemies[i] = findAndKillEnemy(archer[i], board);
        }

        int killCount = 0;
        for (int i = 0; i < killedEnemies.length; i++) {
            if (killedEnemies[i][0] == -1 || board[killedEnemies[i][0]][killedEnemies[i][1]] == 0) {
                continue;
            }

            board[killedEnemies[i][0]][killedEnemies[i][1]] = 0;
            killCount++;
        }
        return killCount;
    }

    private static int moveEnemy(int[][] board) {
        int passCount = 0;

        for (int i = N - 1; i >= 0; i--) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) {
                    if (i == N - 1) {
                        board[i][j] = 0;
                        passCount++;
                        continue;
                    }

                    board[i][j] = 0;
                    board[i + 1][j] = 1;
                }
            }
        }
        return passCount;
    }

    private static int[] findAndKillEnemy(int archerPos, int[][] board) {
        int minY = Math.max(N - D, 0);

        int resultDistance = Integer.MAX_VALUE;
        int[] enemy = new int[]{-1, -1};
        for (int i = N - 1; i >= minY; i--) {
            int extraDistance = D - (N - i);
            int minX = Math.max(archerPos - extraDistance, 0);
            int maxX = Math.min(archerPos + extraDistance, M - 1);

            for (int j = minX; j <= maxX; j++) {
                int distance = Math.abs(archerPos - j) + Math.abs(N - i);

                if (board[i][j] == 1 && distance <= D &&
                    (distance < resultDistance || (distance == resultDistance && enemy[1] > j))) {
                    resultDistance = distance;
                    enemy = new int[]{i, j};
                }
            }
        }

        if (resultDistance == Integer.MAX_VALUE) {
            return enemy;
        }
        return enemy;
    }

    private static int[][] copyBoard() {
        int[][] newBoard = new int[N][M];

        for (int i = 0; i < N; i++) {
            newBoard[i] = Arrays.copyOf(board[i], M);
        }
        return newBoard;
    }
}
