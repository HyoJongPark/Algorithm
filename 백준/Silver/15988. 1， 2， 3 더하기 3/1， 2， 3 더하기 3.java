import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static int T, N, MOD_NUM = 1_000_000_009;
    static int[] dp = new int[1_000_001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i < dp.length; i++) {
            dp[i] = (((dp[i - 1] + dp[i - 2]) % MOD_NUM) + dp[i - 3]) % MOD_NUM;
        }

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }
}
