import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, Q;
    static int[][] position, distance;

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        position = new int[N + 1][2];

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            position[i][0] = Integer.parseInt(st.nextToken());
            position[i][1] = Integer.parseInt(st.nextToken());
        }

        distance = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(distance[i], INF);
        }
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (isFriend(position[i], position[j])) {
                    distance[i][j] = 1;
                    distance[j][i] = 1;
                }
            }
        }

        floyd();

        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (distance[a][b] != INF) {
                sb.append(distance[a][b]).append("\n");
            } else {
                sb.append("-1\n");
            }
        }
        System.out.println(sb);
    }

    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
    }

    private static boolean isFriend(int[] friendA, int[] friendB) {
        return (friendA[0] <= friendB[0] && friendB[0] <= friendA[1]) ||
                (friendA[0] <= friendB[1] && friendB[1] <= friendA[1]) ||
                (friendB[0] < friendA[0] && friendA[1] < friendB[1]);
    }
}
