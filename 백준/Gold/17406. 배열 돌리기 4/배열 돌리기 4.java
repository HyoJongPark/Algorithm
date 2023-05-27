import java.io.*;
import java.util.*;

class Main {

    static int N, M, K, result = Integer.MAX_VALUE;
    static int minX, minY, maxX, maxY;
    static int[][] board;
    static List<int[]> commands = new LinkedList<>();
    static final int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        //initialize board
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        //initialize commands
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            commands.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        permutation(0, new int[K], new boolean[K]);
        System.out.print(result);
    }

    private static void permutation(int cnt, int[] commandSequence, boolean[] visited) {
        if (cnt == K) {
            doCommands(commandSequence);
        }

        for (int i = 0; i < K; i++) {
            if (!visited[i]) {
                visited[i] = true;
                commandSequence[cnt] = i;
                permutation(cnt + 1, commandSequence, visited);
                visited[i] = false;
            }
        }
    }

    private static void doCommands(int[] commandSequence) {
        int[][] copyBoard = copyBoard();
        for (int idx : commandSequence) {
            doCommand(copyBoard, commands.get(idx));
        }
        result = Math.min(result, findArrValue(copyBoard));
    }

    static void doCommand(int[][] copyBoard, int[] command) {
        minX = command[0] - command[2] - 1;
        minY = command[1] - command[2] - 1;
        maxX = command[0] + command[2] - 1;
        maxY = command[1] + command[2] - 1;

        while (minX < maxX && minY < maxY) {
            rotate(copyBoard);
            minX++;
            minY++;
            maxX--;
            maxY--;
        }
    }

    private static int[][] copyBoard() {
        int[][] copyBoard = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyBoard[i][j] = board[i][j];
            }
        }
        return copyBoard;
    }

    static void rotate(int[][] copyBoard) {
        int tmp = copyBoard[minX][minY], direction = 0;
        int nextX = minX, nextY = minY;

        do {
            if (!isValid(nextX + d[direction][0], nextY + d[direction][1])) {
                direction++;
            }

            nextX += d[direction][0];
            nextY += d[direction][1];

            int afterVal = copyBoard[nextX][nextY];
            copyBoard[nextX][nextY] = tmp;
            tmp = afterVal;
        } while (!(nextX == minX + 1 && nextY == minY));
        copyBoard[minX][minY] = tmp;
    }

    static int findArrValue(int[][] copyBoard) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int currentArrVal = 0;

            for (int j = 0; j < M; j++) {
                currentArrVal += copyBoard[i][j];
            }
            result = Math.min(result, currentArrVal);
        }

        return result;
    }

    static boolean isValid(int nextX, int nextY) {
        return minX <= nextX && nextX <= maxX && minY <= nextY && nextY <= maxY;
    }
}