import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, M, R;
    static int[][] board;
    static int[][] d = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

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

        int nextN = N, nextM = M;
        for (int i = 0; i < Math.min(N / 2, M / 2); i++) {
            rotate(i, 2 * nextN + 2 *  nextM - 4);
            nextN -= 2;
            nextM -= 2;
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

    private static void rotate(int layer, int totalLength) {
        int[] tmp = makeInitArr(layer, totalLength);
        int[] next = new int[totalLength];

        for (int i = 0; i < tmp.length; i++) {
            int nextIdx = (i + R) % totalLength;
            next[nextIdx] = tmp[i];
        }

        int x = layer, y = layer, idx = 0, direction = 0;
        while (idx < totalLength) {
            board[x][y] = next[idx++];

            if (!isValid(layer, x + d[direction][0], y + d[direction][1])) {
                direction++;
            }
            x += d[direction][0];
            y += d[direction][1];
        }
    }

    private static boolean isValid(int layer, int nextX, int nextY) {
        return layer <= nextX && nextX < N - layer && layer <= nextY && nextY < M - layer;
    }

    private static int[] makeInitArr(int layer, int totalLength) {
        int[] tmp = new int[totalLength];

        int x = layer, y = layer, idx = 0;
        int direction = 0;
        do {
            tmp[idx++] = board[x][y];

            if (!isValid(layer, x + d[direction][0], y + d[direction][1])) {
                direction++;
            }
            x += d[direction][0];
            y += d[direction][1];
        } while (x != layer || y != layer);

        return tmp;
    }

}
