import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

class Main {
    static final int BOARD_SIZE = 4;
    static final int[][] d = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};

    static int fishCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];
        List<Fish> fishes = new ArrayList<>();

        for (int i = 0; i < BOARD_SIZE; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < BOARD_SIZE; j++) {
                int fishNo = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken()) - 1;

                Fish current = new Fish(fishNo, direction, i, j, false);
                board[i][j] = fishNo;
                fishes.add(current);
            }
        }
        fishes.sort(Fish::compareTo);

        Fish fish = fishes.get(board[0][0] - 1);
        Shark shark = new Shark(fish.fishNo, fish.direction, 0, 0);
        fish.isDead = true;
        board[0][0] = -1;
        dfs(shark, board, fishes);
        System.out.println(fishCount);
    }

    private static void dfs(Shark shark, int[][] board, List<Fish> fishes) {
        moveFishes(board, fishes);

        for (int i = 1; i <= BOARD_SIZE; i++) {
            int nextX = shark.x + i * d[shark.direction][0];
            int nextY = shark.y + i * d[shark.direction][1];

            if (!isValid(nextX, nextY)) break;
            if (board[nextX][nextY] != 0) {
                int fishNo = board[nextX][nextY];
                int[][] copyBoard = copyBoard(board);
                List<Fish> copyFishes = copyFishes(fishes);

                Fish fish = copyFishes.get(fishNo - 1);
                fish.isDead = true;
                copyBoard[nextX][nextY] = -1;
                copyBoard[shark.x][shark.y] = 0;

                dfs(new Shark(fish.fishNo + shark.count, fish.direction, nextX, nextY), copyBoard, copyFishes);
            }
        }

        fishCount = Math.max(fishCount, shark.count);
    }

    private static List<Fish> copyFishes(List<Fish> fishes) {
        return fishes.stream().map((f) -> new Fish(f.fishNo, f.direction, f.x, f.y, f.isDead)).collect(Collectors.toList());
    }

    private static int[][] copyBoard(int[][] board) {
        int[][] newBoard = new int[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

    private static void moveFishes(int[][] board, List<Fish> fishes) {
        for (Fish fish : fishes) {
            if (fish.isDead) continue;

            for (int i = 0; i < d.length; i++) {
                int nextX = fish.x + d[fish.direction][0];
                int nextY = fish.y + d[fish.direction][1];

                if (isValid(nextX, nextY) && board[nextX][nextY] != -1) {
                    int tmp = board[nextX][nextY];
                    board[nextX][nextY] = board[fish.x][fish.y];
                    board[fish.x][fish.y] = tmp;

                    if (tmp != 0) {
                        Fish next = fishes.get(tmp - 1);
                        next.move(fish.x, fish.y);
                    }
                    fish.move(nextX, nextY);
                    break;
                }
                fish.direction = (fish.direction + 1) % d.length;
            }
        }
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < BOARD_SIZE && 0 <= y && y < BOARD_SIZE;
    }

    static class Shark {
        int count;
        int direction;
        int x, y;

        public Shark(int count, int direction, int x, int y) {
            this.count = count;
            this.direction = direction;
            this.x = x;
            this.y = y;
        }
    }

    static class Fish implements Comparable<Fish> {
        int fishNo;   // 1<=x<=16 not duplicate
        int direction; // 0<=x<=7
        int x, y;
        boolean isDead;

        public Fish(int fishNo, int direction, int x, int y, boolean isDead) {
            this.fishNo = fishNo;
            this.direction = direction;
            this.x = x;
            this.y = y;
            this.isDead = isDead;
        }

        public void move(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Fish o) {
            return this.fishNo - o.fishNo;
        }
    }
}
