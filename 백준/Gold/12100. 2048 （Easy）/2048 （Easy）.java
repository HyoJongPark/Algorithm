import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, result = Integer.MIN_VALUE;

    static int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; //상, 하, 좌, 우

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, board);
        System.out.println(result);
    }

    private static void dfs(int depth, int[][] board) {
        if (depth == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    result = Math.max(result, board[i][j]);
                }
            }
            return;
        }

        int[][] newBoard = new int[N][N];
        for (int direction = 0; direction < d.length; direction++) {
            for (int i = 0; i < N; i++) {
                System.arraycopy(board[i], 0, newBoard[i], 0, N);
            }

            move(direction, newBoard);
            dfs(depth + 1, newBoard);
        }
    }

    private static void move(int direction, int[][] board) {
        if (direction == 0) {
            for (int j = 0; j < N; j++) {
                int lastIdx = 0;
                int beforeValue = 0;

                for (int i = 0; i < N; i++) {
                    if (board[i][j] != 0) {
                        if (beforeValue == board[i][j]) {
                            board[lastIdx - 1][j] = beforeValue * 2;
                            beforeValue = 0;
                            board[i][j] = 0;
                        } else {
                            beforeValue = board[i][j];
                            board[i][j] = 0;
                            board[lastIdx++][j] = beforeValue;
                        }
                    }
                }
            }
        } else if (direction == 1) {
            for (int j = 0; j < N; j++) {
                int lastIdx = N - 1;
                int beforeValue = 0;

                for (int i = N - 1; i >= 0; i--) {
                    if (board[i][j] != 0) {
                        if (beforeValue == board[i][j]) {
                            board[lastIdx + 1][j] = beforeValue * 2;
                            beforeValue = 0;
                            board[i][j] = 0;
                        } else {
                            beforeValue = board[i][j];
                            board[i][j] = 0;
                            board[lastIdx--][j] = beforeValue;
                        }
                    }
                }
            }
        } else if (direction == 2) {
            for (int i = 0; i < N; i++) {
                int lastIdx = 0;
                int beforeValue = 0;

                for (int j = 0; j < N; j++) {
                    if (board[i][j] != 0) {
                        if (beforeValue == board[i][j]) {
                            board[i][lastIdx - 1] = beforeValue * 2;
                            beforeValue = 0;
                            board[i][j] = 0;
                        } else {
                            beforeValue = board[i][j];
                            board[i][j] = 0;
                            board[i][lastIdx++] = beforeValue;
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < N; i++) {
                int lastIdx = N - 1;
                int beforeValue = 0;

                for (int j = N - 1; j >= 0; j--) {
                    if (board[i][j] != 0) {
                        if (beforeValue == board[i][j]) {
                            board[i][lastIdx + 1] = beforeValue * 2;
                            beforeValue = 0;
                            board[i][j] = 0;
                        } else {
                            beforeValue = board[i][j];
                            board[i][j] = 0;
                            board[i][lastIdx--] = beforeValue;
                        }
                    }
                }
            }
        }
    }
}
