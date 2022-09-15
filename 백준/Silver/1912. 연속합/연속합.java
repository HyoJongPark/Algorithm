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
        dp = new int[N];

        dp[0] = nums[0];
        MAX = nums[0];
        for (int i = 1; i < N; i++) {
            dp[i] = max(dp[i - 1] + nums[i], nums[i]);
            MAX = max(MAX, dp[i]);
        }

        System.out.println(MAX);
    }
}