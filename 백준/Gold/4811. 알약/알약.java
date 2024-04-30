import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long[] dp = new long[31];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 2; i < dp.length; i++) {
            long count = 0;

            for (int j = 0; j < i; j++) {
                count += dp[j] * dp[i - j - 1];
            }
            dp[i] = count;
        }

        StringBuilder sb = new StringBuilder();
        while ((N = Integer.parseInt(br.readLine())) != 0) {
            sb.append(dp[N]).append("\n");
        }
        System.out.println(sb);
    }
}
