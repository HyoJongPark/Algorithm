import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;
    static int[][] dp;
    static final int MOD_NUM = 1_000_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(0);
            System.exit(0);
        }

        dp = new int[3][N + 1];
        dp[0][2] = dp[1][2] = 1;
        for (int i = 3; i <= N; i++) {
            dp[0][i] = (dp[1][i - 1] + dp[2][i - 1]) % MOD_NUM;
            dp[1][i] = (dp[0][i - 1] + dp[2][i - 1]) % MOD_NUM;
            dp[2][i] = (dp[0][i - 1] + dp[1][i - 1]) % MOD_NUM;
        }

        System.out.println(dp[0][N]);
    }

}
