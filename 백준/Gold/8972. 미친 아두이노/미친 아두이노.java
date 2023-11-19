import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int R, C;
    static int[] currentPos = {};
    static char[][] board;
    static Queue<int[]> arduino = new LinkedList<>();

    static final String GAME_OVER = "kraj ";
    static final int[][] d = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 0}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();

            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'R') {
                    arduino.offer(new int[]{i, j});
                } else if (board[i][j] == 'I') {
                    currentPos = new int[]{i, j};
                }
            }
        }

        char[] movement = br.readLine().toCharArray();
        for (int i = 0; i < movement.length; i++) {
            int currentD = movement[i] - '0' - 1;

            moveJongsu(currentD, i + 1);
            moveArduino(i + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                sb.append(board[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    private static void moveJongsu(int direction, int count) {
        board[currentPos[0]][currentPos[1]] = '.';

        currentPos = new int[]{currentPos[0] + d[direction][0], currentPos[1] + d[direction][1]};
        if (board[currentPos[0]][currentPos[1]] == 'R') {
            System.out.println(GAME_OVER + count);
            System.exit(0);
        }
        board[currentPos[0]][currentPos[1]] = 'I';
    }

    private static void moveArduino(int count) {
        int numberOfArduino = arduino.size();
        char[][] newBoard = new char[R][C];
        for (int i = 0; i < R; i++) {
            Arrays.fill(newBoard[i], '.');
        }
        newBoard[currentPos[0]][currentPos[1]] = 'I';
        boolean[][] check = new boolean[R][C];

        //내가 이동하기 전에 해당 칸이 이미 파괴 된 경우
        for (int i = 0; i < numberOfArduino; i++) {
            int[] current = arduino.poll();
            if (board[current[0]][current[1]] == '.') continue;

            int direction = findDirection(current);
            int[] next = {current[0] + d[direction][0], current[1] + d[direction][1]};

            if (newBoard[next[0]][next[1]] == 'I') {
                System.out.println(GAME_OVER + count);
                System.exit(0);
            }
            if (check[next[0]][next[1]]) {
                newBoard[next[0]][next[1]] = '.';
            } else {
                newBoard[next[0]][next[1]] = 'R';
                check[next[0]][next[1]] = true;
                arduino.offer(next);
            }
        }
        board = newBoard;
    }

    private static int findDirection(int[] arduinoPos) {
        if (currentPos[0] < arduinoPos[0]) {
            if (currentPos[1] < arduinoPos[1]) return 6;
            else if (currentPos[1] > arduinoPos[1]) return 8;
            else return 7;
        } else if (currentPos[0] > arduinoPos[0]) {
            if (currentPos[1] < arduinoPos[1]) return 0;
            else if (currentPos[1] > arduinoPos[1]) return 2;
            else return 1;
        } else {
            if (currentPos[1] < arduinoPos[1]) return 3;
            else if (currentPos[1] > arduinoPos[1]) return 5;
            else return 4;
        }
    }
}
