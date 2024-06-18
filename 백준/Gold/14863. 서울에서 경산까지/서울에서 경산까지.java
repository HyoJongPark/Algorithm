import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, K;
    static Cost[] costs;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        costs = new Cost[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            costs[i] = new Cost(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dp = new int[N][K + 1];
        dp[0][costs[0].walkTime] = costs[0].walkCost;
        dp[0][costs[0].bicycleTime] = Math.max(dp[0][costs[0].bicycleTime], costs[0].bicycleCost);

        int maxCost = Integer.MIN_VALUE;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j <= K; j++) {
                if (dp[i - 1][j] == 0) {
                    continue;
                }

                if (j + costs[i].walkTime <= K) {
                    dp[i][j + costs[i].walkTime] =
                        Math.max(dp[i][j + costs[i].walkTime], dp[i - 1][j] + costs[i].walkCost);

                    if (i == N - 1) {
                        maxCost = Math.max(maxCost, dp[i][j + costs[i].walkTime]);
                    }
                }

                if (j + costs[i].bicycleTime <= K) {
                    dp[i][j + costs[i].bicycleTime] =
                        Math.max(dp[i][j + costs[i].bicycleTime], dp[i - 1][j] + costs[i].bicycleCost);

                    if (i == N - 1) {
                        maxCost = Math.max(maxCost, dp[i][j + costs[i].bicycleTime]);
                    }
                }
            }
        }

        System.out.println(maxCost);
    }

    static class Cost {

        int walkTime, walkCost;
        int bicycleTime, bicycleCost;

        public Cost(int walkTime, int walkCost, int bicycleTime, int bicycleCost) {
            this.walkTime = walkTime;
            this.walkCost = walkCost;
            this.bicycleTime = bicycleTime;
            this.bicycleCost = bicycleCost;
        }
    }
}
