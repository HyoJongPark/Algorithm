import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    private static final int INF = 987654321;
    static int N;
    static int[][] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            Arrays.fill(tree[i], INF);
        }

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1) break;
            tree[a][b] = 1;
            tree[b][a] = 1;
        }

        floyd();
        int count = 0, cost = Integer.MAX_VALUE;
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            int currentMax = Integer.MIN_VALUE;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;

                currentMax = Math.max(currentMax, tree[i][j]);
            }

            if (cost == currentMax) {
                count++;
                result.add(i);
            } else if (cost > currentMax) {
                cost = currentMax;
                count = 1;
                result = new ArrayList<>();
                result.add(i);
            }
        }
        Collections.sort(result);

        StringBuilder sb = new StringBuilder();
        sb.append(cost).append(" ").append(count).append("\n");
        for (Integer i : result) {
            sb.append(i).append(" ");
        }
        System.out.println(sb);
    }

    private static void floyd() {
        for (int mid = 1; mid <= N; mid++) {
            for (int st = 1; st <= N; st++) {
                for (int en = 1; en <= N; en++) {
                    if (tree[st][en] > tree[st][mid] + tree[mid][en]) {
                        tree[st][en] = tree[st][mid] + tree[mid][en];
                    }
                }
            }
        }
    }
}
