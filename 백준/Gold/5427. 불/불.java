import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main {
    static int T, N, M;
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine(), " ");
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            board = new char[N + 1][M + 1];

            Queue<Point> fire = new LinkedList<>();
            Queue<Point> person = new LinkedList<>();
            for (int i = 0; i < N; i++) {
                board[i] = br.readLine().toCharArray();
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == '@') person.offer(new Point(i, j));
                    else if (board[i][j] == '*') fire.offer(new Point(i, j));

                }
            }
            int bfs = BFS(fire, person);
            if (bfs != -1) System.out.println(bfs);
        }
    }

    private static int BFS(Queue<Point> fire, Queue<Point> person) {
        int next_x, next_y, answer = 0;
        while (true) {
            answer++;
            int fireSize = fire.size();
            for (int f = 0; f < fireSize; f++) {
                Point point = fire.poll();
                for (int i = 0; i < 4; i++) {
                    next_x = point.x + dx[i];
                    next_y = point.y + dy[i];

                    if (canExtend(next_x, next_y)) {
                        board[next_x][next_y] = '*';
                        fire.offer(new Point(next_x, next_y));
                    }
                }
            }

            int personSize = person.size();
            for (int p = 0; p < personSize; p++) {
                Point point = person.poll();

                for (int i = 0; i < 4; i++) {
                    next_x = point.x + dx[i];
                    next_y = point.y + dy[i];

                    if (canEscape(next_x, next_y)) return answer;
                    else if (canGo(next_x, next_y)) {
                        board[next_x][next_y] = '@';
                        person.offer(new Point(next_x, next_y));
                    }
                }
            }

            if (person.isEmpty()) {
                System.out.println("IMPOSSIBLE");
                break;
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
}