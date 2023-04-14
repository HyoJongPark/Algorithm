import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    static int N;
    static int[][] sticker, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            sticker = new int[2][N + 1];
            dp = new int[2][N + 1];
            for (int i = 0; i < 2; i++) {
                String[] tmp = br.readLine().split(" ");
                for (int j = 1; j <= N; j++) {
                    sticker[i][j] = Integer.parseInt(tmp[j - 1]);
                }
            }

            int result = findMaxValue();
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static int findMaxValue() {
        dp[0][1] = sticker[0][1];
        dp[1][1] = sticker[1][1];

        for (int i = 2; i <= N; i++) {
            dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + sticker[0][i];
            dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + sticker[1][i];
        }
        return Math.max(dp[0][N], dp[1][N]);
    }
}