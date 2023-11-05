import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int N;
    static String str;
    static int[] dp;

    static final int MAX = 1234567;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        str = br.readLine();
        dp = new int[N];

        Arrays.fill(dp, MAX);
        dp[0] = 0;
        for (int i = 0; i < N; i++) {
            char target = findNextTarget(str.charAt(i));

            for (int j = i + 1; j < N; j++) {
                if (str.charAt(j) == target) {
                    int cost = (j - i) * (j - i) + dp[i];
                    dp[j] = Math.min(cost, dp[j]);
                }
            }
        }

        if (dp[N - 1] == MAX) {
            System.out.println(-1);
        } else {
            System.out.println(dp[N - 1]);
        }
    }

    private static char findNextTarget(char c) {
        if (c == 'B') {
            return 'O';
        } else if (c == 'O') {
            return 'J';
        }
        return 'B';
    }
}
