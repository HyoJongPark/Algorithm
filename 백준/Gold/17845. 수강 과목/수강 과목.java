import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, K;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K + 1][N + 1];

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int priority = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            for (int j = 0; j <= N; j++) {
                if (j >= time) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - time] + priority);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[K][N]);
    }
}
