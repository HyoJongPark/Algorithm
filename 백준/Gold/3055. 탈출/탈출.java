import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main {
    static int N, M;
    static Point target;
    static char[][] board;
    static Queue<Point> water = new LinkedList<>(), hedgehog = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++){
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'D') target = new Point(i, j);
                else if (board[i][j] == '*') water.offer(new Point(i, j));
                else if (board[i][j] == 'S') hedgehog.offer(new Point(i, j));
            }
        }

        int bfs = BFS();
        if (bfs != -1) System.out.println(bfs);
    }

    private static int BFS() {
        int waterSize, hedgehogSize, answer = 0;
        int next_x, next_y;
        while (true) {
            answer++;
            waterSize = water.size();
            for (int w = 0; w < waterSize; w++) {
                Point point = water.poll();

                for (int i = 0; i < 4; i++) {
                    next_x = point.x + dx[i];
                    next_y = point.y + dy[i];

                    if (canExtend(next_x, next_y)) {
                        board[next_x][next_y] = '*';
                        water.offer(new Point(next_x, next_y));
                    }
                }
            }

            hedgehogSize = hedgehog.size();
            for (int h = 0; h < hedgehogSize; h++) {
                Point point = hedgehog.poll();

                for (int i = 0; i < 4; i++) {
                    next_x = point.x + dx[i];
                    next_y = point.y + dy[i];

                    if (arrive(next_x, next_y)) return answer;
                    else if (canGo(next_x, next_y)) {
                        board[next_x][next_y] = 'S';
                        hedgehog.offer(new Point(next_x, next_y));
                    }
                }
            }

            if (hedgehog.isEmpty()) {
                System.out.println("KAKTUS");
                break;
            }
        }

        return -1;
    }

    private static boolean canExtend(int next_x, int next_y) {
        return 0 <= next_x && next_x < N && 0 <= next_y && next_y < M
                && (board[next_x][next_y] == '.' || board[next_x][next_y] == 'S');
    }

    private static boolean arrive(int next_x, int next_y) {
        return target.x == next_x && target.y == next_y;
    }

    private static boolean canGo(int next_x, int next_y) {
        return 0 <= next_x && next_x < N && 0 <= next_y && next_y < M
                && board[next_x][next_y] == '.';
    }
}