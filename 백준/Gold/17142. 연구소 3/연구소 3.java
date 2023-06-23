import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M, result = Integer.MAX_VALUE;
    static int[][] board;
    static List<Virus> virus = new ArrayList<>();

    static final int[][] d = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 2) virus.add(new Virus(i, j, 0));
            }
        }

        selectVirus(0, 0, new Virus[M]);
        if (result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
    }

    private static void selectVirus(int level, int start, Virus[] v) {
        if (level == M) {
            result = Math.min(BFS(v), result);
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            v[level] = virus.get(i);
            selectVirus(level + 1, i + 1, v);
            v[level] = null;
        }
    }

    private static int BFS(Virus[] v) {
        Queue<Virus> q = new LinkedList<>();
        int[][] check = new int[N][N];

        for (int i = 0; i < v.length; i++) {
            check[v[i].x][v[i].y] = -1;
            q.offer(v[i]);
        }
        while (!q.isEmpty()) {
            Virus current = q.poll();

//            if (current.count >= result - 1) return Integer.MAX_VALUE;
            spread(q, check, current);
        }

        return checkVirusSpreadTime(check);
    }

    private static void spread(Queue<Virus> q, int[][] check, Virus current) {
        for (int i = 0; i < d.length; i++) {
            Virus next = new Virus(current.x + d[i][0], current.y + d[i][1], current.count + 1);

            if (isValid(next.x, next.y) && board[next.x][next.y] != 1 && check[next.x][next.y] == 0) {
                q.offer(next);
                check[next.x][next.y] = next.count;
            }
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < N;
    }

    private static int checkVirusSpreadTime(int[][] check) {
        int time = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) {
                    if (check[i][j] == 0) return Integer.MAX_VALUE;
                    else time = Math.max(check[i][j], time);
                }
            }
        }
        return time;
    }

    static class Virus {

        int x, y;
        int count;

        public Virus(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}
