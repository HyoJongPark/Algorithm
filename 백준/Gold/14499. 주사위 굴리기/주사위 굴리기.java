import java.io.*;
import java.util.*;

class Main {

    static int N, M, x, y, K;
    static int[][] board;
    static int[] commands, dice;

    static final int[][] d = {{-1, -1}, {0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //initialize value
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        //initialize board
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        //initialize command, dice
        commands = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dice = new int[6];

        StringBuilder sb = new StringBuilder();
        for (int command : commands) {
            Integer value;
            if ((value = doCommand(command)) != null) {
                sb.append(value).append("\n");
            }
        }
        System.out.print(sb);
    }

    static Integer doCommand(int command) {
        if (isValid(x + d[command][0], y + d[command][1])) {
            moveDice(command);
            return swapValue(x + d[command][0], y + d[command][1]);
        }
        return null;
    }

    static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }

    static void moveDice(int command) {
        if (command == 1) {
            moveEast();
        } else if (command == 2) {
            moveWest();
        } else if (command == 3) {
            moveNorth();
        } else {
            moveSouth();
        }
    }

    static int swapValue(int nextX, int nextY) {
        x = nextX;
        y = nextY;
        if (board[x][y] == 0) {
            board[x][y] = dice[0];
        } else {
            dice[0] = board[x][y];
            board[x][y] = 0;
        }

        return dice[2];
    }

    static void moveEast() {
        int tmp = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[2];
        dice[2] = dice[5];
        dice[5] = tmp;
    }

    static void moveWest() {
        int tmp = dice[0];
        dice[0] = dice[5];
        dice[5] = dice[2];
        dice[2] = dice[4];
        dice[4] = tmp;
    }

    static void moveNorth() {
        int tmp = dice[0];
        dice[0] = dice[3];
        dice[3] = dice[2];
        dice[2] = dice[1];
        dice[1] = tmp;
    }

    static void moveSouth() {
        int tmp = dice[0];
        dice[0] = dice[1];
        dice[1] = dice[2];
        dice[2] = dice[3];
        dice[3] = tmp;
    }
}