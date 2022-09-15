import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Math.max;

class Main {
    static int N, MAX;
    static int[] nums, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[nums.length];
        dp[0] = 1;
        MAX = dp[0];

        for (int i = 1; i < dp.length; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i] && dp[j] > max) max = dp[j];
            }

            dp[i] = max + 1;
            MAX = max(MAX, dp[i]);
        }

        System.out.println(MAX);
    }
}