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
    static boolean[] check;
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
        check = new boolean[virus.size()];
        selectAndCountTIme(0, 0);

        if (result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
    }

    private static void selectAndCountTIme(int level, int start) {
        if (level == M) {
            result = Math.min(BFS(), result);
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            check[i] = true;
            selectAndCountTIme(level + 1, i + 1);
            check[i] = false;
        }
    }

    private static int BFS() {
        Queue<Virus> q = new LinkedList<>();
        int[][] copyBoard = new int[N][N];
        //copy board
        for (int i = 0; i < N; i++) {
            copyBoard[i] = Arrays.copyOf(board[i], board[i].length);
        }
        //initialize board & queue
        for (int i = 0; i < virus.size(); i++) {
            if (check[i]) {
                q.offer(virus.get(i));
                copyBoard[virus.get(i).x][virus.get(i).y] = -1;
            }
        }

        int time = 0;
        while (!q.isEmpty()) {
            Virus current = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + d[i][0];
                int nextY = current.y + d[i][1];

                if (isValid(nextX, nextY) && copyBoard[nextX][nextY] != 1 && copyBoard[nextX][nextY] != -1) {
                    if (current.count + 1 >= result) return Integer.MAX_VALUE;
                    q.offer(new Virus(nextX, nextY, current.count + 1));
                    copyBoard[nextX][nextY] = -1;
                    time = current.count + 1;
                }
            }
        }

        return canSpreadEveryWhere(copyBoard) ? time : Integer.MAX_VALUE;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < N;
    }

    private static boolean canSpreadEveryWhere(int[][] copyBoard) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (copyBoard[i][j] == 0 || copyBoard[i][j] == 2) return false;
            }
        }
        return true;
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
