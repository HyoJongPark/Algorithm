import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, R;
    static int[][] board;
    static int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < R; i++) {
            for (int layer = 0; layer < Math.min(N, M) / 2; layer++) {
                rotate(layer);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(board[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void rotate(int layer) {
        int x = layer, y = layer;
        int temp = board[x][y];

        int idx = 0;
        while (idx < 4) {
            int nextX = x + d[idx][0];
            int nextY = y + d[idx][1];

            if (isValid(nextX, nextY, layer)) {
                board[x][y] = board[nextX][nextY];
                x = nextX;
                y = nextY;
            } else {
                idx++;
            }
        }

        board[layer + 1][layer] = temp;
    }

    private static boolean isValid(int nextX, int nextY, int layer) {
        return layer <= nextX && nextX < N - layer && layer <= nextY && nextY < M - layer;
    }

}
