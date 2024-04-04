import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    static int N;
    static int[] dp = new int[3_000_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = Math.min(dp[i - 1] + 1, dp[i]);

            dp[i * 3] = Math.min(dp[i] + 1, dp[i * 3]);
            dp[i * 2] = Math.min(dp[i] + 1, dp[i * 2]);
        }
        System.out.println(dp[N]);
    }
}
