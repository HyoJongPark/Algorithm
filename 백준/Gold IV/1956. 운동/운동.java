import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int V, E;
    static int[][] distance;

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        distance = new int[V + 1][V + 1];

        for (int i = 1; i <= V; i++) {
            Arrays.fill(distance[i], INF);
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            distance[a][b] = cost;
        }

        floyd();

        int result = findResult();
        System.out.println(result == INF ? -1 : result);
    }

    private static void floyd() {
        for (int mid = 1; mid <= V; mid++) {
            for (int st = 1; st <= V; st++) {
                for (int en = 1; en <= V; en++) {
                    if (distance[st][en] > distance[st][mid] + distance[mid][en]) {
                        distance[st][en] = distance[st][mid] + distance[mid][en];
                    }
                }
            }
        }
    }

    private static int findResult() {
        int result = INF;

        for (int st = 1; st <= V; st++) {
            for (int en = 1; en <= V; en++) {
                if (distance[st][en] != INF && distance[en][st] != INF) {
                    result = Math.min(result, distance[st][en] + distance[en][st]);
                }
            }
        }
        return result;
    }
}
