import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int C, N;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        dp = new int[C + 101];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int numberOfCustomer = Integer.parseInt(st.nextToken());

            for (int j = numberOfCustomer; j < dp.length; j++) {
                if (dp[j - numberOfCustomer] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], cost + dp[j - numberOfCustomer]);
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = C; i < dp.length; i++) {
            result = Math.min(dp[i], result);
        }
        System.out.println(result);
    }
}
