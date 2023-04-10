import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int R, C, M;
    static Shark[][] board;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new Shark[R + 1][C + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken()), z = Integer.parseInt(st.nextToken());
            board[r][c] = new Shark(r, c, s, d, z);
        }

        //move 낚시왕
        int result = 0;
        for (int position = 1; position <= C; position++) {
            result += findShark(position);
            findAndMoveShark();
        }
        System.out.println(result);
    }

    private static int findShark(int position) {
        for (int i = 1; i <= R; i++) {
            if (board[i][position] != null) {
                int sharkSize = board[i][position].z;
                board[i][position] = null;
                return sharkSize;
            }
        }
        return 0;
    }

    private static void findAndMoveShark() {
        Shark[][] oldBoard = board;
        board = new Shark[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                if (oldBoard[i][j] != null) {
                    Shark next = moveShark(oldBoard[i][j]);


                    //현재 칸이 비었거나, 이미 있던 상어의 크기가 더 작으면 잡아먹기
                    if (board[next.x][next.y] == null || (board[next.x][next.y] != null && board[next.x][next.y].z < next.z)) {
                        board[next.x][next.y] = next;
                    }
                }
            }
        }
    }

    private static Shark moveShark(Shark current) {
        int nextX = current.x, nextY = current.y;
        for (int i = 0; i < current.s; i++) {
            nextX += dx[current.d];
            nextY += dy[current.d];

            if (!isValid(nextX, nextY)) {
                if (current.d % 2 == 0) {
                    current.d--;
                } else {
                    current.d++;
                }
                nextX += 2 * dx[current.d];
                nextY += 2 * dy[current.d];
            }
        }
        return new Shark(nextX, nextY, current.s, current.d, current.z);
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 < nextX && nextX <= R && 0 < nextY && nextY <= C;
    }

    static class Shark {
        int x, y;
        int s, d, z;

        public Shark(int x, int y, int s, int d, int z) {
            this.x = x;
            this.y = y;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}