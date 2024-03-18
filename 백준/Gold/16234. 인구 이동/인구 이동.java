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
    static int N, L, R;
    static int[][] board;
    static int[][] d = {{0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        boolean status = false;
        int count = 0;
        do {
            List<int[]>[][] nodes = new List[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    nodes[i][j] = new ArrayList<>();
                }
            }

            boolean isOpened = openCountry(nodes);
            if (!isOpened) break;

            move(nodes);
            count++;
        } while (true);
        System.out.println(count);
    }

    private static void move(List<int[]>[][] nodes) {
        boolean[][] check = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!check[i][j]) {
                    bfs(i, j, check, nodes);
                }
            }
        }
    }

    private static void bfs(int x, int y, boolean[][] check, List<int[]>[][] nodes) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> countries = new ArrayList<>();

        q.offer(new int[]{x, y});
        countries.add(new int[]{x, y});
        check[x][y] = true;
        int totalPerson = board[x][y];
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int[] next : nodes[current[0]][current[1]]) {
                if (!check[next[0]][next[1]]) {
                    q.offer(new int[]{next[0], next[1]});
                    countries.add(new int[]{next[0], next[1]});
                    check[next[0]][next[1]] = true;
                    totalPerson += board[next[0]][next[1]];
                }
            }
        }

        int eachPerson = totalPerson / countries.size();
        for (int[] country : countries) {
            board[country[0]][country[1]] = eachPerson;
        }
    }

    private static boolean openCountry(List<int[]>[][] nodes) {
        boolean isOpened = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                isOpened = checkEachCountry(i, j, nodes) || isOpened;
            }
        }
        return isOpened;
    }

    private static boolean checkEachCountry(int x, int y, List<int[]>[][] nodes) {
        boolean isOpened = false;
        for (int i = 0; i < d.length; i++) {
            int nextX = x + d[i][0];
            int nextY = y + d[i][1];

            if (isValid(nextX, nextY)) {
                int count = Math.abs(board[x][y] - board[nextX][nextY]);

                if (L <= count && count <= R) {
                    nodes[x][y].add(new int[]{nextX, nextY});
                    nodes[nextX][nextY].add(new int[]{x, y});
                    isOpened = true;
                }
            }
        }
        return isOpened;
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
