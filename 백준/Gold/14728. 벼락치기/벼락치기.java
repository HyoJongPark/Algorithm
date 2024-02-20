import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, T;
    static int[][] chapter;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        chapter = new int[N + 1][2];
        dp = new int[N + 1][T + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            chapter[i][0] = Integer.parseInt(st.nextToken());
            chapter[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int time = 0; time <= T; time++) {
                if (chapter[i][0] <= time) {
                    dp[i][time] = Math.max(dp[i - 1][time], dp[i - 1][time - chapter[i][0]] + chapter[i][1]);
                } else {
                    dp[i][time] = dp[i - 1][time];
                }
            }
        }
        System.out.println(dp[N][T]);
    }
}
