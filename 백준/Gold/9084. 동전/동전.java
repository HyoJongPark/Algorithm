import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int T, N, M;
    static int[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            coins = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            M = Integer.parseInt(br.readLine());

            int[] dp = new int[M + 1];
            for (int coin : coins) {
                for (int i = 1; i <= M; i++) {
                    if (i - coin > 0) {
                        dp[i] = dp[i] + dp[i - coin];
                    } else if (i - coin == 0) {
                        dp[i]++;
                    }
                }
            }
            sb.append(dp[M]).append("\n");
        }
        System.out.print(sb);
    }
}
