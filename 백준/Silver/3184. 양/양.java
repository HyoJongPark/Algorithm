import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M, resultWolf = 0, resultSheep = 0;
    static char[][] board;
    static boolean[][] check;

    static final int[][] d = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        check = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != '#' && !check[i][j]) {
                    check[i][j] = true;
                    BFS(i, j);
                }
            }
        }
        System.out.printf("%d %d", resultSheep, resultWolf);
    }

    private static void BFS(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{x, y});

        boolean canEscape = canEscape(x, y);
        int numberOfSheep = board[x][y] == 'o' ? 1 : 0;
        int numberOfWolf = board[x][y] == 'v' ? 1 : 0;

        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current[0] + d[i][0];
                int nextY = current[1] + d[i][1];

                if (isValid(nextX, nextY) && board[nextX][nextY] != '#' && !check[nextX][nextY]) {
                    check[nextX][nextY] = true;
                    numberOfSheep += board[nextX][nextY] == 'o' ? 1 : 0;
                    numberOfWolf += board[nextX][nextY] == 'v' ? 1 : 0;
                    q.offer(new int[]{nextX, nextY});
                } else if (!isValid(nextX, nextY)) {
                    canEscape = true;
                }
            }
        }

        if (canEscape) return;
        if (numberOfWolf >= numberOfSheep) {
            resultWolf += numberOfWolf;
        } else {
            resultSheep += numberOfSheep;
        }
    }

    private static boolean canEscape(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nextX = x + d[i][0];
            int nextY = y + d[i][1];

            if (!isValid(nextX, nextY)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }
}
