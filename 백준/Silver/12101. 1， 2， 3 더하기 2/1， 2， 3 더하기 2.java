import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, K, count = 0;
    static int[] dp = new int[11];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dfs(0, 0);
        System.out.println(-1);
    }

    private static void dfs(int depth, int num) {
        if (num > N) return;
        if (num == N && ++ count == K) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < depth; i++) {
                sb.append(dp[i]).append("+");
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
            System.exit(0);
        }

        for (int i = 1; i <= 3; i++) {
            dp[depth] = i;
            dfs(depth + 1, num + i);
        }
    }
}
