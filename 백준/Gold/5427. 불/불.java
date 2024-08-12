import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int T, N, M;
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            board = new char[N + 1][M + 1];

            Queue<Point> fire = new LinkedList<>();
            Queue<Point> person = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                board[i] = br.readLine().toCharArray();

                for (int j = 0; j < M; j++) {
                    if (board[i][j] == '@') {
                        person.offer(new Point(i, j));
                    } else if (board[i][j] == '*') {
                        fire.offer(new Point(i, j));
                    }
                }
            }

            int result = BFS(fire, person);
            if (result != -1) {
                sb.append(result);
            } else {
                sb.append("IMPOSSIBLE");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }

    private static int BFS(Queue<Point> fire, Queue<Point> person) {
        int answer = 0;

        while (!person.isEmpty()) {
            answer++;

            int fireSize = fire.size();
            for (int f = 0; f < fireSize; f++) {
                Point current = fire.poll();

                for (int i = 0; i < 4; i++) {
                    int nextX = current.x + dx[i];
                    int nextY = current.y + dy[i];

                    if (canExtend(nextX, nextY)) {
                        board[nextX][nextY] = '*';
                        fire.offer(new Point(nextX, nextY));
                    }
                }
            }

            int personSize = person.size();
            for (int p = 0; p < personSize; p++) {
                Point current = person.poll();

                for (int i = 0; i < 4; i++) {
                    int nextX = current.x + dx[i];
                    int nextY = current.y + dy[i];

                    if (canEscape(nextX, nextY)) {
                        return answer;
                    } else if (canGo(nextX, nextY)) {
                        board[nextX][nextY] = '@';
                        person.offer(new Point(nextX, nextY));
                    }
                }
            }
        }
        return -1;
    }

    private static boolean canEscape(int next_x, int next_y) {
        return next_x < 0 || next_x >= N || next_y < 0 || next_y >= M;
    }

    private static boolean canExtend(int next_x, int next_y) {
        return 0 <= next_x && next_x < N && 0 <= next_y && next_y < M
                && (board[next_x][next_y] == '.' || board[next_x][next_y] == '@');
    }

    private static boolean canGo(int next_x, int next_y) {
        return 0 <= next_x && next_x < N && 0 <= next_y && next_y < M
                && board[next_x][next_y] == '.';
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
