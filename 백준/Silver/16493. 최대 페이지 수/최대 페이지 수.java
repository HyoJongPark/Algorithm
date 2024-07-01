import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M;
    static int[] dp;
    static int[][] chapter;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[N + 1];
        chapter = new int[M + 1][2];
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            chapter[i][0] = Integer.parseInt(st.nextToken());
            chapter[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int j = 1; j <= M; j++) {
            for (int k = N; k - chapter[j][0] >= 0; k--) {
                dp[k] = Math.max(dp[k], dp[k - chapter[j][0]] + chapter[j][1]);
            }
        }
        System.out.println(dp[N]);
    }
}
