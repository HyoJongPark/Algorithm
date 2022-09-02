import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int N, target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        target = Integer.parseInt(tmp[1]);
        int[] dp = new int[target + 1];
        for (int i = 0; i < N; i++) {
            int coin = Integer.parseInt(br.readLine());

            for (int j = 1; j <= target; j++) {
                if (j - coin > 0) dp[j] = dp[j] + dp[j - coin];
                else if (j - coin == 0) dp[j]++;
            }
        }

        System.out.println(dp[target]);
    }
}