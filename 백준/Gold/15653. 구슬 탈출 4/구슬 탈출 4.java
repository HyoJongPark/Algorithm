import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static Point redBall, blueBall;
    static char[][] board;
    static boolean[][][][] check;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        check = new boolean[N][M][N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'R') {
                    redBall = new Point(i, j, 0);
                } else if (board[i][j] == 'B') {
                    blueBall = new Point(i, j, 0);
                }
            }
        }

        System.out.println(BFS());
    }

    private static int BFS() {
        Queue<Point[]> q = new LinkedList<>();
        q.offer(new Point[]{redBall, blueBall});
        check[redBall.x][redBall.y][blueBall.x][blueBall.y] = true;

        while (!q.isEmpty()) {
            Point[] balls = q.poll();
            Point currentRed = balls[0], currentBlue = balls[1];

            for (int i = 0; i < 4; i++) {
                //blue 공 이동
                boolean bluePathHall = false;
                Point nextBlue = new Point(currentBlue.x, currentBlue.y, currentBlue.count + 1);
                while (isValid(nextBlue.x + dx[i], nextBlue.y+ dy[i])) {
                    nextBlue.x += dx[i];
                    nextBlue.y += dy[i];
                    if (board[nextBlue.x][nextBlue.y] == 'O') {
                        bluePathHall = true;
                        break;
                    }
                }
                if (bluePathHall) continue;

                //red 공 이동
                Point nextRed = new Point(currentRed.x, currentRed.y, currentRed.count + 1);
                while (isValid(nextRed.x + dx[i], nextRed.y + dy[i])) {
                    nextRed.x += dx[i];
                    nextRed.y += dy[i];
                    if (board[nextRed.x][nextRed.y] == 'O') return currentRed.count + 1;
                }

                //두 공의 위치가 같다면 위치 재조정
                if (nextRed.equals(nextBlue)) {
                    if (i == 0) {
                        if (currentRed.x > currentBlue.x) nextRed.x++;
                        else nextBlue.x++;
                    } else if (i == 1) {
                        if (currentRed.x < currentBlue.x) nextRed.x--;
                        else nextBlue.x--;
                    } else if (i == 2) {
                        if (currentRed.y > currentBlue.y) nextRed.y++;
                        else nextBlue.y++;
                    } else {
                        if (currentRed.y < currentBlue.y) nextRed.y--;
                        else nextBlue.y--;
                    }
                }

                //이미 방문한 장소가 아니면, 방문체크 후 큐에 삽입
                if (!check[nextRed.x][nextRed.y][nextBlue.x][nextBlue.y]) {
                    q.offer(new Point[]{nextRed, nextBlue});
                    check[nextRed.x][nextRed.y][nextBlue.x][nextBlue.y] = true;
                }
            }
        }

        return -1;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M
                && board[nextX][nextY] != '#';
    }

    static class Point {
        int x, y;
        int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public boolean equals(Object obj) {
            return x == ((Point) obj).x && y == ((Point) obj).y;
        }
    }
}
