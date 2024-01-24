import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
    static int N, M, result = 0;
    static char[][] board;
    static boolean[][] check, finish;
    static HashMap<Character, int[]> direction = new HashMap<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        check = new boolean[N][M];
        finish = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }
        direction.put('U', new int[]{-1, 0});
        direction.put('D', new int[]{1, 0});
        direction.put('L', new int[]{0, -1});
        direction.put('R', new int[]{0, 1});

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!check[i][j]) {
                    check[i][j] = true;
                    dfs(i, j);
                }
            }
        }
        System.out.println(result);
    }

    private static void dfs(int x, int y) {
        int[] d = direction.get(board[x][y]);
        int nextX = x + d[0];
        int nextY = y + d[1];

        if (!check[nextX][nextY]) {
            check[nextX][nextY] = true;
            dfs(nextX, nextY);
        } else if (!finish[nextX][nextY]) {
            result++;
        }
        finish[x][y] = true;
    }
}
