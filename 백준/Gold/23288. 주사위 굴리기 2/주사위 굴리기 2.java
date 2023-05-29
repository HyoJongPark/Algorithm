import java.io.*;
import java.util.*;

class Main {
    static int N, M, K, x = 0, y = 0, direction = 1;
    static int[][] board, check;

    //밑, 뒤, 위, 앞, 동, 서
    static final HashMap<Integer, Integer> numAndScore = new HashMap<>();
    static final int[] dice = {6, 5, 1, 2, 3, 4};
    static final int[][] d = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; //북 - 동 - 남 - 서

    //48start
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //initialize
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        check = new int[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        //find sequnce nums
        int countNum = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (check[i][j] == 0) {
                    check[i][j] = countNum;
                    BFS(i, j, countNum++);
                }
            }
        }

        //move
        int result = 0;
        for (int i = 0; i < K; i++) {
            result += doCommand();
            direction = changeDirection();
        }
        System.out.println(result);
    }

    //섬 번호 초기화
    static void BFS(int x, int y, int countNum) {
        Queue<int[]> q = new LinkedList<>();

        int count = 1, currentNum = board[x][y];
        q.offer(new int[]{x, y});
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current[0] + d[i][0];
                int nextY = current[1] + d[i][1];

                if (isValid(nextX, nextY) && currentNum == board[nextX][nextY] && check[nextX][nextY] == 0) {
                    q.offer(new int[]{nextX, nextY});
                    check[nextX][nextY] = countNum;
                    count++;
                }
            }
        }
        numAndScore.put(countNum, count * currentNum);
    }

    //이동 && 점수 계산
    static int doCommand() {
        if (!isValid(x + d[direction][0], y + d[direction][1])) {
            direction = (direction + 2) % 4;
        }

        moveDice();
        x += d[direction][0];
        y += d[direction][1];
        return numAndScore.get(check[x][y]);
    }

    static int changeDirection() {
        if (dice[0] > board[x][y]) {
            return (direction + 1) % 4;
        } else if (dice[0] < board[x][y]) {
            return (3 + direction) % 4;
        }

        return direction;
    }

    static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }

    static void moveDice() {
        if (direction == 0) {
            moveNorth();
        } else if (direction == 1) {
            moveEast();
        } else if (direction == 2) {
            moveSouth();
        } else {
            moveWest();
        }
    }

    static void moveNorth() {
        int tmp = dice[0];
        dice[0] = dice[3];
        dice[3] = dice[2];
        dice[2] = dice[1];
        dice[1] = tmp;
    }

    static void moveEast() {
        int tmp = dice[0];
        dice[0] = dice[4];
        dice[4] = dice[2];
        dice[2] = dice[5];
        dice[5] = tmp;
    }

    static void moveSouth() {
        int tmp = dice[0];
        dice[0] = dice[1];
        dice[1] = dice[2];
        dice[2] = dice[3];
        dice[3] = tmp;
    }

    static void moveWest() {
        int tmp = dice[0];
        dice[0] = dice[5];
        dice[5] = dice[2];
        dice[2] = dice[4];
        dice[4] = tmp;
    }
}
