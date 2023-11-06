import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int T, k;
    static int[][] coin, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        coin = new int[k][2];
        dp = new int[k + 1][T + 1];

        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            coin[i][0] = Integer.parseInt(st.nextToken());
            coin[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= k; i++) {
            int currentCoin = coin[i - 1][0];
            int currentAmount = coin[i - 1][1];
            dp[i - 1][0] = 1;

            for (int target = 1; target <= T; target++) {
                for (int cnt = 0; cnt <= currentAmount; cnt++) {
                    if (target - (currentCoin * cnt) >= 0) {
                        dp[i][target] += dp[i - 1][target - (currentCoin * cnt)];
                    }
                }
            }
        }
        System.out.println(dp[k][T]);
    }
}
