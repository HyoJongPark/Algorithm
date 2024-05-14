import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static final int INF = 987654321;

    static int T, N, M, K;
    static int[] roomInfo;
    static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
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
                int d = Integer.parseInt(st.nextToken());

                distance[a][b] = d;
                distance[b][a] = d;
            }

            floyd();

            K = Integer.parseInt(br.readLine());
            roomInfo = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int result = Integer.MAX_VALUE, targetRoom = -1;
            for (int i = 1; i <= N; i++) {
                int currentDist = 0;

                for (int roomNo : roomInfo) {
                    currentDist += distance[roomNo][i];
                }

                if (result > currentDist) {
                    result = currentDist;
                    targetRoom = i;
                }
            }
            sb.append(targetRoom).append("\n");
        }
        System.out.print(sb);
    }

    private static void floyd() {
        for (int mid = 1; mid <= N; mid++) {
            for (int st = 1; st <= N; st++) {
                for (int en = 1; en <= N; en++) {
                    distance[st][en] = Math.min(distance[st][mid] + distance[mid][en], distance[st][en]);
                }
            }
        }
    }
}
