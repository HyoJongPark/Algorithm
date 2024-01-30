import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int R, C, T;
    static int[][] board;
    static int[] cleaner = null; //upper side
    static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int current = Integer.parseInt(st.nextToken());
                if (cleaner == null && current == -1) {
                    cleaner = new int[]{i, j};
                }
                board[i][j] = current;
            }
        }

        /*
        1. 확산
            - 상하좌우로 확산
            - 공기청정기가 있거나 밖이면 확산 x
            - 확산 양은 N/5(소수점 버림)
            - 남는 양은 N - (N/5 * 확산 방향 수)
        2. 공기청정기 작동
            - 위쪽은 반시계로 순환
            - 아래는 시계로 순환
            - 경로에 있는 것들은 바람의 방향대로 이동
            - 공기청정기로 들어간 것은 정화
         */
        while (T-- > 0) {
            spreadDust();
            operateAirPurifier(new int[]{cleaner[0], cleaner[1]}, 0, false); //move upper side
            operateAirPurifier(new int[]{cleaner[0] + 1, cleaner[1]}, 2, true); //move down side
        }

        int count = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] != -1) count += board[i][j];
            }
        }
        System.out.println(count);
    }

    private static void spreadDust() {
        int[][] newBoard = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == -1) continue;

                int spreadAmount = board[i][j] / 5, count = 0;
                for (int[] direction : d) {
                    int nextX = i + direction[0];
                    int nextY = j + direction[1];

                    if (isValidDustPos(nextX, nextY) && board[nextX][nextY] != -1) {
                        count++;
                        newBoard[nextX][nextY] += spreadAmount;
                    }
                }
                newBoard[i][j] += board[i][j] - spreadAmount * count;

            }
        }
        newBoard[cleaner[0]][cleaner[1]] = -1;
        newBoard[cleaner[0] + 1][cleaner[1]] = -1;
        board = newBoard;
    }

    private static void operateAirPurifier(int[] position, int direction, boolean isDownSide) {
        position = new int[]{position[0] + d[direction][0], position[1] + d[direction][1]};
        board[position[0]][position[1]] = 0;
        while (board[position[0]][position[1]] != -1) {
            //move
            int[] nextPosition = {position[0] + d[direction][0], position[1] + d[direction][1]};

            if (!isValidCleanerPos(nextPosition[0], nextPosition[1], isDownSide)) {
                direction = direction + (isDownSide ? -1 : 1);

                if (direction == 4) direction = 0;
                else if (direction == -1) direction = 3;
                nextPosition = new int[]{position[0] + d[direction][0], position[1] + d[direction][1]};
            }

            //change
            int currentValue = board[position[0]][position[1]];
            if (board[nextPosition[0]][nextPosition[1]] == -1) {
                break;
            }
            board[position[0]][position[1]] = board[nextPosition[0]][nextPosition[1]];
            board[nextPosition[0]][nextPosition[1]] = currentValue;

            position = nextPosition;
        }
    }

    private static boolean isValidCleanerPos(int x, int y, boolean isDownSide) {
        return (isDownSide ? cleaner[0] + 1 : 0) <= x && x < (isDownSide ? R : cleaner[0] + 1) && 0 <= y && y < C;
    }

    private static boolean isValidDustPos(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }
}
