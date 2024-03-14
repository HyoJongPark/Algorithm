import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N][10_001];

        int[] memory = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] reBootCost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 10_001; j++) {
                if (i == 0) {
                    if (j >= reBootCost[i]) dp[i][j] = memory[i];
                } else {
                    if (j >= reBootCost[i]) dp[i][j] = Math.max(dp[i - 1][j - reBootCost[i]] + memory[i], dp[i - 1][j]);
                    else dp[i][j] = dp[i - 1][j];
                }

                if (dp[i][j] >= M) {
                    result = Math.min(result, j);
                }
            }
        }
        System.out.println(result);
    }
}
