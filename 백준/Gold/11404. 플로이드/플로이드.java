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
            distance[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            distance[a][b] = Math.min(c, distance[a][b]); //시작 도시와 도착 도시가 같은 경우는 없다는데 왜 있지?
        }

        floyd();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (distance[i][j] == INF) {
                    sb.append(0).append(" ");
                    continue;
                }
                sb.append(distance[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void floyd() {
        for (int k = 1; k <= N; k++) { //중간
            for (int i = 1; i <= N; i++) { //시작
                for (int j = 1; j <= N; j++) { //끝
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
    }
}
