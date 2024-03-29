import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[][] dp;
    static boolean[] check;
    static List<Integer>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][2];
        check = new boolean[N + 1];
        nodes = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nodes[a].add(b);
            nodes[b].add(a);
        }

        DFS(1);
        System.out.println(Math.min(dp[1][0], dp[1][1]));
    }

    private static void DFS(int current) {
        check[current] = true;
        dp[current][0] = 0;
        dp[current][1] = 1;

        for (int next : nodes[current]) {
            if (!check[next]) {
                DFS(next);
                dp[current][0] += dp[next][1];
                dp[current][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }
    }
}
