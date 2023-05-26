import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    static int N;
    static int[][] board;
    static boolean[][] check;
    static List<SpreadRatio> spreadRatios = new ArrayList<>();
    static int[][] d = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        check = new boolean[N][N];
        initializeSpreadRatio();
        
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(tornado(N / 2, N / 2));
    }

    private static void initializeSpreadRatio() {
        //수직
        spreadRatios.add(new SpreadRatio(2, 0, 5, false));
        spreadRatios.add(new SpreadRatio(2, 1, 2, false));
        spreadRatios.add(new SpreadRatio(1, 1, 7, false));
        spreadRatios.add(new SpreadRatio(2, 3, 2, false));
        spreadRatios.add(new SpreadRatio(1, 3, 7, false));
        //대각선
        spreadRatios.add(new SpreadRatio(1, 0, 10, true));
        spreadRatios.add(new SpreadRatio(1, 1, 10, true));
        spreadRatios.add(new SpreadRatio(1, 2, 1, true));
        spreadRatios.add(new SpreadRatio(1, 3, 1, true));
    }

    private static int tornado(int x, int y) {
        int direction = -1, amountOfOutSand = 0;
        check[x][y] = true;

        do {
            //move tornado
            int nextDirection = (direction + 1) % 4;
            int nextX = x + d[nextDirection][0], nextY = y + d[nextDirection][1];
            if (!check[nextX][nextY]) {
                x = nextX;
                y = nextY;
                direction = nextDirection;
            } else {
                x += d[direction][0];
                y += d[direction][1];
            }

            check[x][y] = true;
            amountOfOutSand += spreadSand(x, y, direction);
        } while (!(x == 0 && y == 0));

        return amountOfOutSand;
    }

    private static int spreadSand(int x, int y, int direction) {
        int amountOfOutSand = 0;
        int currentAmountOfSand = board[x][y];

        if (board[x][y] / 10 > 0) {
            for (SpreadRatio spreadRatio : spreadRatios) {
                int nextDirection = (direction + spreadRatio.direction) % 4;
                int nextX = x + spreadRatio.position * (spreadRatio.isDiagonal ? spreadRatio.d[nextDirection][0] : d[nextDirection][0]);
                int nextY = y + spreadRatio.position * (spreadRatio.isDiagonal ? spreadRatio.d[nextDirection][1] : d[nextDirection][1]);

                int nextAmountOfSand = board[x][y] * spreadRatio.ratio / 100;
                currentAmountOfSand -= nextAmountOfSand;
                if (isValid(nextX, nextY)) {
                    board[nextX][nextY] += nextAmountOfSand;
                } else {
                    amountOfOutSand += nextAmountOfSand;
                }
            }
        }

        //move sand to alpha
        board[x][y] = 0;
        x += d[direction][0];
        y += d[direction][1];
        if (isValid(x, y)) {
            board[x][y] += currentAmountOfSand;
        } else {
            amountOfOutSand += currentAmountOfSand;
        }
        return amountOfOutSand;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < N;
    }

    private static class SpreadRatio {
        int position, direction;
        int ratio;
        boolean isDiagonal;
        final int[][] d = {{-1, -1}, {1, -1}, {1, 1}, {-1, 1}};

        public SpreadRatio(int position, int direction, int ratio, boolean isDiagonal) {
            this.position = position;
            this.direction = direction;
            this.ratio = ratio;
            this.isDiagonal = isDiagonal;
        }
    }
}