import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    private static final int INF = 987654321;
    static int N, M;
    static int[][] distance;
    static List<Integer>[][] history;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        distance = new int[N + 1][N + 1];
        history = new List[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(distance[i], INF);

            for (int j = 1; j <= N; j++) {
                history[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            distance[a][b] = Math.min(distance[a][b], cost);
        }

        floyd();

        StringBuilder sb = new StringBuilder();
        printCost(sb);
        printHistory(sb);
        System.out.println(sb);
    }

    private static void printHistory(StringBuilder sb) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j || distance[i][j] == INF) {
                    sb.append("0\n");
                } else {
                    sb.append(history[i][j].size() + 2).append(" ").append(i).append(" ");
                    for (int next : history[i][j]) {
                        sb.append(next).append(" ");
                    }
                    sb.append(j).append("\n");
                }
            }
        }
    }

    private static void printCost(StringBuilder sb) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (distance[i][j] == INF) sb.append("0 ");
                else sb.append(distance[i][j]).append(" ");
            }

            sb.append("\n");
        }
    }

    private static void floyd() {
        for (int mid = 1; mid <= N; mid++) {
            for (int st = 1; st <= N; st++) {
                for (int en = 1; en <= N; en++) {
                    if (st == en) continue;

                    if (distance[st][en] > distance[st][mid] + distance[mid][en]) {
                        distance[st][en] = distance[st][mid] + distance[mid][en];
                        makeHistory(st, en, mid);
                    }
                }
            }
        }
    }

    private static void makeHistory(int st, int en, int mid) {
        history[st][en] = new ArrayList<>();

        history[st][en].addAll(history[st][mid]);
        history[st][en].add(mid);
        history[st][en].addAll(history[mid][en]);
    }
}
