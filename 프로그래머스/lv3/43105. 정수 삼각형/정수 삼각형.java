import static java.lang.Math.max;

class Solution {
    public int solution(int[][] triangle) {
        int N = triangle.length;
        int[][] dp = new int[N][N];
        dp[N - 1] = triangle[N - 1];

        for (int i = N - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = triangle[i][j] + max(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }
        return dp[0][0];
    }
}