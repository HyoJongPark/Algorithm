import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
    static int N, M, K;
    static int[][] board;
    static int[][][] check;
    static Shark[] sharks;

    static int[][] d = {{-1, -1}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        //초기화 부분
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        check = new int[N][N][2];
        sharks = new Shark[M + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] != 0) {
                    sharks[board[i][j]] = new Shark(i, j);
                    check[i][j][0] = board[i][j];
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sharks[i].direction = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= M; i++) {
            for (int direction = 1; direction <= 4; direction++) {
                int[] priority = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

                sharks[i].priority.put(direction, priority);
            }
        }

        int playTime = playGame();
        if (playTime > 1000) {
            System.out.println(-1);
        } else {
            System.out.println(playTime);
        }
    }

    private static int playGame() {
        int time = 0;

        while (++time <= 1001) {
            int countDeadShark = moveSharks(time);

            if (countDeadShark == M - 1) return time - 1;
        }
        return time;
    }

    private static int moveSharks(int time) {
        int countDeadShark = 0;
        int[][][] newCheck = new int[N][N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 2; k++) {
                    newCheck[i][j][k] = check[i][j][k];
                }
            }
        }


        for (int sharkNo = 1; sharkNo <= M; sharkNo++) {
            if (!sharks[sharkNo].isDead) {
                moveEachShark(sharkNo, time, newCheck);
            } else {
                countDeadShark++;
            }
        }
        check = newCheck;
        return countDeadShark;
    }

    private static void moveEachShark(int sharkNo, int time, int[][][] newCheck) {
        Shark shark = sharks[sharkNo];

        //다음 방향 결정 및 이동
        int[] tmpPosition = null;
        for (int direction : shark.getNextDirection()) {
            int nextX = shark.x + d[direction][0];
            int nextY = shark.y + d[direction][1];

            if (isValid(nextX, nextY)) {
                if (check[nextX][nextY][0] == 0 || check[nextX][nextY][1] < time - K) {
                    //빈 공간이면 바로 이동
                    if (newCheck[nextX][nextY][0] != 0 && newCheck[nextX][nextY][1] == time) {
                        //해당 칸에 상어가 존재하면, 생사결
                        if (sharkNo > newCheck[nextX][nextY][0]) {
                            shark.isDead = true;
                        } else {
                            sharks[newCheck[nextX][nextY][0]].isDead = true;

                            shark.move(nextX, nextY, direction);
                            newCheck[nextX][nextY][0] = sharkNo;
                            newCheck[nextX][nextY][1] = time;
                        }
                    } else {
                        //해당 칸에 상어가 없다면, 이동
                        shark.move(nextX, nextY, direction);
                        newCheck[nextX][nextY][0] = sharkNo;
                        newCheck[nextX][nextY][1] = time;
                    }
                    return;
                } else if (tmpPosition == null && check[nextX][nextY][0] == sharkNo && check[nextX][nextY][1] >= time - K) {
                    //처음으로 마주친 내 이전 공간은 저장 후 마지막에 이동하지 않았으면 해당 칸으로 이동
                    tmpPosition = new int[]{nextX, nextY, direction};
                }
            }
        }

        if (tmpPosition == null) {
            System.out.println(1);
        }
        shark.move(tmpPosition[0], tmpPosition[1], tmpPosition[2]);
        newCheck[tmpPosition[0]][tmpPosition[1]][0] = sharkNo;
        newCheck[tmpPosition[0]][tmpPosition[1]][1] = time;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < N;
    }

    static class Shark {
        int x, y, direction;
        boolean isDead = false;
        HashMap<Integer, int[]> priority = new HashMap<>();

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int[] getNextDirection() {
            return this.priority.get(this.direction);
        }

        public void move(int nextX, int nextY, int direction) {
            this.x = nextX;
            this.y = nextY;
            this.direction = direction;
        }
    }
}
