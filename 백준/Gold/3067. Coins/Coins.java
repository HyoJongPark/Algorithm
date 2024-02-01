import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int T, N, target;
    static int[] coins;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            target = Integer.parseInt(br.readLine());

            coins = new int[N + 1];
            dp = new int[N + 1][target + 1];
            for (int i = 1; i <= N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
                dp[i][coins[i]]++;
            }

            calculate();
            sb.append(dp[N][target]).append("\n");
        }
        System.out.print(sb);
    }

    private static void calculate() {
        for (int i = 1; i <= N; i++) {
            for (int current = 1; current <= target; current++) {
                if (current < coins[i]) {
                    dp[i][current] = dp[i - 1][current];
                    continue;
                }
                dp[i][current] += dp[i - 1][current] + dp[i][current - coins[i]];
            }
        }
    }
}
