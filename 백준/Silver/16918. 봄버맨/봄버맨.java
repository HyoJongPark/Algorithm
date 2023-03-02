import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int R, C, N;
    static char[][] board;
    static boolean[][] check;
    static Queue<Bomb> q = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        StringBuilder sb = new StringBuilder();
        //특정 조건을 만족했을 경우 바로 출력 후 종료, 아니라면, 그냥 진행
        if (N == 1) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    sb.append(board[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        } else if (N % 2 == 0) {
            for (int i = 0; i < R; i++) {
                sb.append("O".repeat(Math.max(0, C))).append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }

        //board를 탐색 후 check
        int time = 1;
        check = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] == 'O') {
                    q.offer(new Bomb(i, j));
                    check[i][j] = true;
                }
            }
        }
        while (time < N) {
            time += bomb();
            editBoard();
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (check[i][j]) sb.append('O');
                else sb.append('.');
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int bomb() {
        while (!q.isEmpty()) {
            Bomb bomb = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = bomb.x + dx[i];
                int nextY = bomb.y + dy[i];

                if (isValid(nextX, nextY)) {
                    check[nextX][nextY] = true;
                }
            }
        }

        return 2;
    }

    private static void editBoard() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                check[i][j] = !check[i][j];
                if (check[i][j]) {
                    q.offer(new Bomb(i, j));
                }
            }
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < R && 0 <= nextY && nextY < C && !check[nextX][nextY];
    }

    static class Bomb {
        int x, y;

        public Bomb(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}