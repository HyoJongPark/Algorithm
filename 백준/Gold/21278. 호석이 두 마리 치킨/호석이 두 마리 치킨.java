import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    private static final int INF = 987654321;

    static int N, M;
    static int[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        distance = new int[N + 1][N + 1];

        for (int i = 0; i < N + 1; i++) {
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
        int[] result = findResult();
        System.out.printf("%d %d %d", result[0], result[1], 2 * result[2]);
    }

    private static int[] findResult() {
        int minCost = Integer.MAX_VALUE, nodeA = 0, nodeB = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                int sum = 0;

                for (int k = 1; k <= N; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    sum += Math.min(distance[i][k], distance[j][k]);
                }

                if (sum < minCost) {
                    minCost = sum;
                    nodeA = i;
                    nodeB = j;
                }
            }
        }
        return new int[]{nodeA, nodeB, minCost};
    }

    private static void floyd() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (distance[i][k] != INF && distance[k][j] != INF) {
                        distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    }
                }
            }
        }
    }

    static class Node {
        int num, cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }
}
