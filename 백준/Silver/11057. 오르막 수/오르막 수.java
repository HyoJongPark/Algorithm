import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    private static final int MOD_NUM = 10_007;
    static int N;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][10];

        Arrays.fill(dp[0], 1);
        for (int i = 1; i <= N; i++) {
            dp[i][9] = 1;
            for (int j = 8; j >= 0; j--) {
                dp[i][j] = (dp[i][j + 1] + dp[i - 1][j]) % MOD_NUM;
            }
        }
        System.out.println(dp[N][0]);
    }
}
