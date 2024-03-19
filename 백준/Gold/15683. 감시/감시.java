import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int N, M, result;
    static int[][] board;
    static List<Cctv> cctvList = new ArrayList<>();
    static int[][] d = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        int numberOfBlindSpot = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    cctvList.add(new Cctv(board[i][j], i, j, new int[]{0}));
                } else if (board[i][j] == 2) {
                    cctvList.add(new Cctv(board[i][j], i, j, new int[]{0, 2}));
                } else if (board[i][j] == 3) {
                    cctvList.add(new Cctv(board[i][j], i, j, new int[]{0, 1}));
                } else if (board[i][j] == 4) {
                    cctvList.add(new Cctv(board[i][j], i, j, new int[]{0, 1, 3}));
                } else if (board[i][j] == 5) {
                    cctvList.add(new Cctv(board[i][j], i, j, new int[]{0, 1, 2, 3}));
                } else if (board[i][j] == 0){
                    numberOfBlindSpot++;
                }
            }
        }

        result = numberOfBlindSpot;
        dfs(0, numberOfBlindSpot);
        System.out.println(result);
    }

    private static void dfs(int depth, int numberOfBlindSpot) {
        if (depth == cctvList.size()) {
            result = Math.min(numberOfBlindSpot, result);
            return;
        }

        Cctv current = cctvList.get(depth);
        for (int i = 0; i < 4; i++) {
            int nextNumberOfBlindSpot = makeBlindSpotToSecuritySpot(current, i, numberOfBlindSpot);
            dfs(depth + 1, nextNumberOfBlindSpot);
            makeSecuritySpotToBlindSpot(current, i);
        }
    }

    private static int makeBlindSpotToSecuritySpot(Cctv current, int i, int numberOfBlindSpot) {
        for (int direction : current.directions) {
            int nextX = current.x + d[(direction + i) % 4][0];
            int nextY = current.y + d[(direction + i) % 4][1];

            while (isValid(nextX, nextY) && (board[nextX][nextY] != 6)) {
                if (board[nextX][nextY] == 0) numberOfBlindSpot--;
                else if (board[nextX][nextY] > 0) {
                    nextX += d[(direction + i) % 4][0];
                    nextY += d[(direction + i) % 4][1];
                    continue;
                }
                board[nextX][nextY]--;

                nextX += d[(direction + i) % 4][0];
                nextY += d[(direction + i) % 4][1];
            }
        }
        return numberOfBlindSpot;
    }

    private static void makeSecuritySpotToBlindSpot(Cctv current, int i) {
        for (int direction : current.directions) {
            int nextX = current.x + d[(direction + i) % 4][0];
            int nextY = current.y + d[(direction + i) % 4][1];

            while (isValid(nextX, nextY) && (board[nextX][nextY] != 6)) {
                if (board[nextX][nextY] > 0) {
                    nextX += d[(direction + i) % 4][0];
                    nextY += d[(direction + i) % 4][1];
                    continue;
                }

                board[nextX][nextY]++;

                nextX += d[(direction + i) % 4][0];
                nextY += d[(direction + i) % 4][1];
            }
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }

    static class Cctv {
        int categoryNumber;
        int x, y;
        int[] directions;

        public Cctv(int categoryNumber, int x, int y, int[] directions) {
            this.categoryNumber = categoryNumber;
            this.x = x;
            this.y = y;
            this.directions = directions;
        }
    }
}
