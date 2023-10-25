import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, result = Integer.MAX_VALUE;
    static int[] scv = new int[3];
    static int[][][] dp = new int[61][61][61];
    static int[][] d = {{-9,-3,-1},{-9,-1,-3},{-3,-9,-1},{-3,-1,-9},{-1,-9,-3},{-1,-3,-9}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            scv[i] = Integer.parseInt(st.nextToken());
        }

        DFS(scv[0], scv[1], scv[2], 0);
        System.out.println(result);
    }

    private static void DFS(int scv1, int scv2, int scv3, int count) {
        if (scv1 == 0 && scv2 == 0 && scv3 == 0) {
            result = Math.min(result, count);
            return;
        }
        if (result <= count) return;

        for (int i = 0; i < d.length; i++) {
            int next1 = Math.max(scv1 + d[i][0], 0);
            int next2 = Math.max(scv2 + d[i][1], 0);
            int next3 = Math.max(scv3 + d[i][2], 0);

            if (dp[next1][next2][next3] == 0 || dp[next1][next2][next3] > count + 1) {
                dp[next1][next2][next3] = count + 1;
                DFS(next1, next2, next3, count + 1);
            }
        }
    }
}
