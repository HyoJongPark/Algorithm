import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, K;
    static int[] board;
    static boolean[] hasRobot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        hasRobot = new boolean[N];

        int count = 0;
        do {
            rotateBeltWithRobot();
            moveRobot();
            putRobotOnBelt();
            count++;
        } while (checkBoard() < K);

        System.out.println(count);
    }

    private static void rotateBeltWithRobot() {
        rotateBelt();
        rotateRobot();
    }

    private static void rotateBelt() {
        int[] copy = new int[2 * N];
        for (int i = 0; i < 2 * N - 1; i++) {
            copy[i + 1] = board[i];
        }
        copy[0] = board[2 * N - 1];
        board = copy;
    }

    private static void rotateRobot() {
        boolean[] copy = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            copy[i + 1] = hasRobot[i];
        }
        copy[N - 1] = false;
        hasRobot = copy;
    }

    private static void moveRobot() {
        for (int i = N - 2; i > 0; i--) {
            if (!hasRobot[i]) continue;

            if (board[i + 1] != 0 && !hasRobot[i + 1]) {
                board[i + 1] -= 1;
                hasRobot[i + 1] = true;
                hasRobot[i] = false;
            }
        }
    }

    private static void putRobotOnBelt() {
        if (board[0] != 0) {
            board[0] -= 1;
            hasRobot[0] = true;
        }
    }

    private static long checkBoard() {
        return Arrays.stream(board)
                .filter(k -> k == 0)
                .count();
    }
}
