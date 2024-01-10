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
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        distance = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(distance[i], INF);
            distance[i][i] = 0;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            distance[a][b] = 1;
            distance[b][a] = 1;
        }

        floyd();
        System.out.println(findMinPeople());
    }

    private static int findMinPeople() {
        int minValue = Integer.MAX_VALUE, num = 0;

        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                sum += distance[i][j];
            }

            if (sum < minValue) {
                minValue = sum;
                num = i;
            }
        }
        return num;
    }

    private static void floyd() {
        for (int mid = 1; mid <= N; mid++) {
            for (int st = 1; st <= N; st++) {
                for (int en = 1; en <= N; en++) {
                    distance[st][en] = Math.min(distance[st][en], distance[st][mid] + distance[mid][en]);
                }
            }
        }
    }
}
