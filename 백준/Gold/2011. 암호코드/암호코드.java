import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static char[] nums;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nums = br.readLine().toCharArray();
        dp = new int[nums.length + 1];

        dp[0] = 1;
        for (int i = 1; i <= nums.length; i++) {
            int current = nums[i - 1] - '0';
            if (current != 0) {
                dp[i] = dp[i - 1];
            }

            if (i == 1) continue;

            int before = nums[i - 2] - '0';
            if (before == 1 || (before == 2 && current <= 6)) {
                dp[i] = (dp[i] + dp[i - 2]) % 1_000_000;
            }
        }
        System.out.println(dp[nums.length]);
    }
}
