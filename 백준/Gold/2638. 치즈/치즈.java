import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[][] board;
    static boolean[][] check;
    static int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        int numberOfCheese = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());

                if (board[i][j] == 1) {
                    numberOfCheese++;
                }
            }
        }

        if (numberOfCheese == 0) {
            System.out.println(0);
            System.exit(0);
        }

        int count = 0;
        int day = 0;
        while (count != N * M) {
            check = new boolean[N][M];
            day++;

            count = BFS();
            count += removeCheese();
        }
        System.out.println(day);
    }

    private static int BFS() {
        Queue<int[]> q = new LinkedList<>();
        int count = 1;

        q.offer(new int[]{0, 0}); //가장자리는 항상 치즈가 아니다.
        check[0][0] = true;
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < d.length; i++) {
                int nextX = current[0] + d[i][0];
                int nextY = current[1] + d[i][1];

                if (isValid(nextX, nextY) && !check[nextX][nextY]) {
                    if (board[nextX][nextY] == 1) {
                        board[nextX][nextY] = 2;
                        check[nextX][nextY] = true;
                    } else {
                        q.offer(new int[]{nextX, nextY});
                        check[nextX][nextY] = true;
                        count++;
                    }
                }
            }
        }

        return count;
    }

    private static int removeCheese() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 2) {
                    count += melt(i, j);
                }
            }
        }
        return count;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }

    private static int melt(int x, int y) {
        int count = 0;
        for (int i = 0; i < d.length; i++) {
            int nextX = x + d[i][0];
            int nextY = y + d[i][1];

            if (isValid(nextX, nextY) && board[nextX][nextY] == 0 && check[nextX][nextY]) {
                count++;
            }
        }

        if (count >= 2) {
            check[x][y] = false;
            board[x][y] = 0;
            return 1;
        }
        board[x][y] = 1;
        return 0;
    }
}
