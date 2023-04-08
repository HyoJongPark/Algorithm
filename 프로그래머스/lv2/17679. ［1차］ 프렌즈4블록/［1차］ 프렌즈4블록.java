class Solution {

    static int N, M;
    static char[][] gameBoard;
    static boolean[][] check;
    static int[] dx = {1, 0, 1};
    static int[] dy = {0, 1, 1};
    private static final char EMPTY_BLOCK = '\u0000';

    public static void main(String[] args) {
//        new Solution().solution(6, 6, new String[]{"TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ"});
        int solution = new Solution().solution(2, 2, new String[]{"AA", "AA"});
        System.out.println(solution);
    }

    public int solution(int m, int n, String[] board) {
        N = m;
        M = n;
        gameBoard = new char[N][M];
        check = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            gameBoard[i] = board[i].toCharArray();
        }

        //playGame
        int result = 0;
        while (checkBoard()) {
            //remove
            result += removeEmptyBlock();
            //move
            moveBlock();
            check = new boolean[N][M];
        }
        return result;
    }

    private boolean checkBoard() {
        boolean status = false;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M - 1; j++) {
                char current = gameBoard[i][j];
                if (current == EMPTY_BLOCK) continue;
                status = canRemove(0, current, i, j) || status;
            }
        }

        return status;
    }

    private int removeEmptyBlock() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (check[i][j]) {
                    count++;
                    gameBoard[i][j] = EMPTY_BLOCK;
                }
            }
        }
        return count;
    }

    private void moveBlock() {
        for (int j = 0; j < M; j++) {
            for (int i = N - 1; i > 0; i--) {
                if (gameBoard[i][j] == EMPTY_BLOCK) {
                    int nextIndex = i - 1;
                    while (nextIndex > 0 && gameBoard[nextIndex][j] == EMPTY_BLOCK) {
                        nextIndex--;
                    }

                    if (gameBoard[nextIndex][j] != EMPTY_BLOCK) {
                        gameBoard[i][j] = gameBoard[nextIndex][j];
                        gameBoard[nextIndex][j] = EMPTY_BLOCK;
                    }
                }
            }
        }
    }

    private boolean canRemove(int level, char current, int i, int j) {
        if (level == 3) {
            check[i][j] = true;
            return true;
        }

        int nextX = i + dx[level];
        int nextY = j + dy[level];
        if (isValid(nextX, nextY) && gameBoard[nextX][nextY] == current) {
            boolean status = canRemove(level + 1, current, i, j);
            if (status) {
                check[nextX][nextY] = status;
            }
            return status;
        }

        return false;
    }

    private boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }
}