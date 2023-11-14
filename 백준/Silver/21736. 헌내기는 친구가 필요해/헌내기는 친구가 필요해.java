import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static char[][] board;
    static boolean[][] check;
    static int[][] d = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        check = new boolean[N][M];

        int[] start = {0, 0};
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();

            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'I') {
                    start = new int[]{i, j};
                }
            }
        }

        int result = BFS(start);
        if (result == 0) {
            System.out.println("TT");
        } else {
            System.out.println(result);
        }
    }

    private static int BFS(int[] start) {
        Queue<int[]> q = new LinkedList<>();
        int count = 0;

        q.offer(start);
        check[start[0]][start[1]] = true;
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < d.length; i++) {
                int nextX = current[0] + d[i][0];
                int nextY = current[1] + d[i][1];

                if (isValid(nextX, nextY) && board[nextX][nextY] != 'X' && !check[nextX][nextY]) {
                    check[nextX][nextY] = true;
                    q.offer(new int[]{nextX, nextY});

                    if (board[nextX][nextY] == 'P') count++;
                }
            }
        }
        return count;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }
}
