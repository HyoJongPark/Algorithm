import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {
    static int N, M, K, maxLength = Integer.MIN_VALUE;
    static String[] keys;
    static char[][] board;
    static Map<String, Integer> map = new HashMap<>();
    static final int[][] d = {{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        keys = new String[K];
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }
        for (int i = 0; i < K; i++) {
            String input = br.readLine();
            map.put(input, 0);
            keys[i] = input;
            maxLength = Math.max(maxLength, input.length());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                DFS(i, j, String.valueOf(board[i][j]));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(map.get(key)).append("\n");
        }
        System.out.println(sb);
    }

    private static void DFS(int x, int y, String current) {
        if (map.containsKey(current)) {
            map.put(current, map.get(current) + 1);
        }
        if (current.length() >= maxLength) return;

        for (int i = 0; i < d.length; i++) {
            int nextX = x + d[i][0];
            int nextY = y + d[i][1];

            if (nextX < 0) nextX = N - 1;
            else if (nextX >= N) nextX = 0;
            if (nextY < 0) nextY = M - 1;
            else if (nextY >= M) nextY = 0;

            DFS(nextX, nextY, current + board[nextX][nextY]);
        }
    }
}
