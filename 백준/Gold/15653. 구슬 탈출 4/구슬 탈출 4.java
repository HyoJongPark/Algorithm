import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static char[][] board;
    static int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        int[] redPos = new int[2];
        int[] bluePos = new int[2];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();

            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'B') bluePos = new int[]{i, j};
                else if (board[i][j] == 'R') redPos = new int[]{i, j, 0};
            }
        }

        int result = bfs(redPos, bluePos);
        System.out.println(result);
    }

    private static int bfs(int[] redPos, int[] bluePos) {
        Queue<int[][]> q = new LinkedList<>();
        boolean[][][][] check = new boolean[N][M][N][M];

        q.offer(new int[][]{redPos, bluePos});
        check[redPos[0]][redPos[1]][bluePos[0]][bluePos[1]] = true;
        while (!q.isEmpty()) {
            int[][] balls = q.poll();
            int[] currentRed = balls[0];
            int[] currentBlue = balls[1];

            for (int i = 0; i < d.length; i++) {
                //moveBlue
                int[] nextBlue = new int[]{currentBlue[0], currentBlue[1]};
                boolean blueIntoHole = false;

                while (isValid(nextBlue[0]  + d[i][0], nextBlue[1] + d[i][1])) {
                    nextBlue[0] += d[i][0];
                    nextBlue[1] += d[i][1];

                    if (board[nextBlue[0]][nextBlue[1]] == 'O') {
                        blueIntoHole = true;
                        break;
                    }
                }
                if (blueIntoHole) continue;

                //move red
                int[] nextRed = new int[]{currentRed[0], currentRed[1], currentRed[2] + 1};

                while (isValid(nextRed[0]  + d[i][0], nextRed[1] + d[i][1])) {
                    nextRed[0] += d[i][0];
                    nextRed[1] += d[i][1];

                    if (board[nextRed[0]][nextRed[1]] == 'O') return nextRed[2];
                }

                //위치 재조정
                if (nextBlue[0] == nextRed[0] && nextBlue[1] == nextRed[1]) {
                    rePosition(currentBlue, currentRed, nextBlue, nextRed, i);
                }

                if (!check[nextRed[0]][nextRed[1]][nextBlue[0]][nextBlue[1]]) {
                    q.offer(new int[][]{nextRed, nextBlue});
                    check[nextRed[0]][nextRed[1]][nextBlue[0]][nextBlue[1]] = true;
                }
            }
        }
        return - 1;
    }

    //d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static void rePosition(int[] currentBlue, int[] currentRed, int[] nextBlue, int[] nextRed, int direction) {
        if (direction == 0) {
            if (currentBlue[1] > currentRed[1]) nextRed[1]--;
            else nextBlue[1]--;
        } else if (direction == 1) {
            if (currentBlue[1] < currentRed[1]) nextRed[1]++;
            else nextBlue[1]++;
        } else if (direction == 2) {
            if (currentBlue[0] > currentRed[0]) nextRed[0]--;
            else nextBlue[0]--;
        } else {
            if (currentBlue[0] < currentRed[0]) nextRed[0]++;
            else nextBlue[0]++;
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M
                && board[nextX][nextY] != '#';
    }
}
