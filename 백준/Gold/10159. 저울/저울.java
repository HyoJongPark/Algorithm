import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[][] distance;

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        distance = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(distance[i], INF);
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            distance[a][b] = 1;
            distance[b][a] = -1;
        }

        floyd();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            int count = -1;

            for (int j = 1; j <= N; j++) {
                if (distance[i][j] == INF) count++;
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    private static void floyd() {
        for (int mid = 1; mid <= N; mid++) {
            for (int start = 1; start <= N; start++) {
                for (int end = 1; end <= N; end++) {
                    if (distance[start][mid] != INF && distance[start][mid] == distance[mid][end]) {
                        distance[start][end] = distance[start][mid];
                    }
                }
            }
        }
    }
}
