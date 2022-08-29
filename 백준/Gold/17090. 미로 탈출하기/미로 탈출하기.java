import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    static int N;
    static int M;
    static char[][] board;
    static boolean[][] check;
    static boolean[][] canEscape;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmp[0];
        M = tmp[1];
        board = new char[N][M];
        check = new boolean[N][M];
        canEscape = new boolean[N][M];
        for (int i = 0; i < N; i++) board[i] = br.readLine().toCharArray();

        int cnt = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                check[x][y] = true;
                if (DFS(x, y) == 1) {
                    canEscape[x][y] = true;
                    cnt += 1;
                }
                check[x][y] = false;
            }
        }

        System.out.println(cnt);
    }

    private static int DFS(int x, int y) {
        if (canEscape[x][y]) return 1;
        char command = board[x][y];
        int next_x = x, next_y = y;
        int cnt = 0;

        if (command == 'U') next_x--;
        else if (command == 'R') next_y++;
        else if (command == 'D') next_x++;
        else next_y--;

        if (validPosition(next_x, next_y) && !check[next_x][next_y]) {
            check[next_x][next_y] = true;
            cnt += DFS(next_x, next_y);
            check[next_x][next_y] = false;
        } else if (!validPosition(next_x, next_y)) {
            return 1;
        }

        if (cnt == 1) canEscape[next_x][next_y] = true;
        return cnt;
    }

    private static boolean validPosition(int next_x, int next_y) {
        return 0 <= next_x && next_x < N && 0 <= next_y && next_y < M;
    }
}