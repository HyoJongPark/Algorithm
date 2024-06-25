import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M, R;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int command = Integer.parseInt(st.nextToken());

            runCommand(command);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void runCommand(int command) {
        switch (command) {
            case 1:
                upAndDownInversion();
                break;

            case 2:
                leftAndRightInversion();
                break;

            case 3:
                rotateRight();
                break;

            case 4:
                rotateLeft();
                break;

            case 5:
                command5();
                break;

            case 6:
                command6();
                break;
        }
    }

    private static void upAndDownInversion() {
        for (int x = 0; x < N / 2; x++) {
            int dest = N - x - 1;

            for (int y = 0; y < M; y++) {
                swap(x, y, dest, y);
            }
        }
    }

    private static void leftAndRightInversion() {
        for (int y = 0; y < M / 2; y++) {
            int dest = M - y - 1;

            for (int x = 0; x < N; x++) {
                swap(x, y, x, dest);
            }
        }
    }

    private static void swap(int x1, int y1, int x2, int y2) {
        int tmp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = tmp;
    }

    private static void rotateRight() {
        int[][] tmpBoard = new int[M][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmpBoard[j][N - i - 1] = board[i][j];
            }
        }
        int tmp = N;
        N = M;
        M = tmp;

        board = tmpBoard;
    }

    private static void rotateLeft() {
        int[][] tmpBoard = new int[M][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                tmpBoard[M - j - 1][i] = board[i][j];
            }
        }
        int tmp = N;
        N = M;
        M = tmp;

        board = tmpBoard;
    }

    private static void command5() {
        int[][] tmpBoard = new int[N][M];

        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M / 2; j++) {
                //4 -> 1
                tmpBoard[i][j] = board[N / 2 + i][j];
                //3 -> 4
                tmpBoard[N / 2 + i][j] = board[N / 2 + i][M / 2 + j];
                //2 -> 3
                tmpBoard[N / 2 + i][M / 2 + j] = board[i][M / 2 + j];
                //1 -> 2
                tmpBoard[i][M / 2 + j] = board[i][j];
            }
        }
        board = tmpBoard;
    }

    private static void command6() {
        int[][] tmpBoard = new int[N][M];

        for (int i = 0; i < N / 2; i++) {
            for (int j = 0; j < M / 2; j++) {
                //1 -> 4
                tmpBoard[N / 2 + i][j] = board[i][j];
                //4 -> 3
                tmpBoard[N / 2 + i][M / 2 + j] = board[N / 2 + i][j];
                //3 -> 2
                tmpBoard[i][M / 2 + j] = board[N / 2 + i][M / 2 + j];
                //2 -> 1
                tmpBoard[i][j] = board[i][M / 2 + j];
            }
        }
        board = tmpBoard;
    }
}
