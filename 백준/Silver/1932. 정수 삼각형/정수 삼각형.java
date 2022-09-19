import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[][] nums, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[i], -1);
            for (int j = 0; j <= i; j++) {
                nums[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++) dp[N - 1][i] = nums[N - 1][i];

        System.out.println(findMaxTriangle(0, 0));;
    }

    private static int findMaxTriangle(int level, int current) {
        if (level == N - 1) return dp[level][current];

        if (dp[level][current] == -1) {
            dp[level][current] = Math.max(findMaxTriangle(level + 1, current), findMaxTriangle(level + 1, current + 1))
                    + nums[level][current];
        }
        return dp[level][current];
    }
}