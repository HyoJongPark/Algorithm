import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {
    static int N;
    static int[] dp;
    static HashMap<Integer, String> map = new HashMap<>();

    static {
        map.put(1, "SK");
        map.put(2, "CY");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[1001];

        //1. 상근, 2. 창영
        dp[1] = 2;
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 1;
        for (int i = 5; i <= N; i++) {
            if (dp[i - 1] == 2 || dp[i - 3] == 2 || dp[i - 4] == 2) dp[i] = 1;
            else dp[i] = 2;
        }
        System.out.println(map.get(dp[N]));
    }
}
