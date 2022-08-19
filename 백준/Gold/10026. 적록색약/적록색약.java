import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Main {
    static int N;
    static int normal_answer = 0;
    static int blind_answer = 0;
    static char[][] board;
    static boolean[][] normal_visited;
    static boolean[][] blindness_visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new char[N][N];
        normal_visited = new boolean[N][N];
        blindness_visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!normal_visited[i][j]) {
                    normal_BFS(i, j);
                    normal_answer++;
                }

                if (!blindness_visited[i][j]) {
                    blind_BFS(i, j);
                    blind_answer++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(normal_answer).append(" ").append(blind_answer);
        System.out.println(sb);
    }

    private static boolean isValidNormal(int next_x, int next_y) {
        return 0 <= next_x && next_x < N && 0 <= next_y && next_y < N && !normal_visited[next_x][next_y];
    }

    private static boolean isValidBlind(int next_x, int next_y) {
        return 0 <= next_x && next_x < N && 0 <= next_y && next_y < N && !blindness_visited[next_x][next_y];
    }

    private static void normal_BFS(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        normal_visited[x][y] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();

            for (int i = 0; i < 4; i++) {
                int next_x = point.x + dx[i];
                int next_y = point.y + dy[i];

                if (isValidNormal(next_x, next_y) && board[x][y] == board[next_x][next_y]) {
                    q.offer(new Point(next_x, next_y));
                    normal_visited[next_x][next_y] = true;
                }
            }
        }
    }

    private static void blind_BFS(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(x, y));
        blindness_visited[x][y] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();

            for (int i = 0; i < 4; i++) {
                int next_x = point.x + dx[i];
                int next_y = point.y + dy[i];

                if (isValidBlind(next_x, next_y)) {
                    if (board[x][y] == board[next_x][next_y]) {
                        q.offer(new Point(next_x, next_y));
                        blindness_visited[next_x][next_y] = true;
                    } else if ((board[x][y] == 'R' || board[x][y] == 'G')
                            && (board[next_x][next_y] == 'R' || board[next_x][next_y] == 'G')) {
                        q.offer(new Point(next_x, next_y));
                        blindness_visited[next_x][next_y] = true;
                    }
                }
            }
        }
    }
}