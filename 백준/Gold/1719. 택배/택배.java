import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    private static final int INF = 987654321;
    static int N, M;
    static int[][] distance, check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        distance = new int[N + 1][N + 1];
        check = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(distance[i], INF);

            for (int j = 1; j <= N; j++) {
                check[i][j] = j;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            distance[a][b] = distance[b][a] = cost;
        }

        floyd();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) sb.append("-");
                else sb.append(check[i][j]);

                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void floyd() {
        for (int mid = 1; mid <= N; mid++) {
            for (int st = 1; st <= N; st++) {
                for (int en = 1; en <= N; en++) {
                    if (distance[st][en] > distance[st][mid] + distance[mid][en]) {
                        distance[st][en] = distance[st][mid] + distance[mid][en];
                        check[st][en] = check[st][mid];
                    }
                }
            }
        }
    }
}
