import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

class Main {
    static int N, target;
    static int[] dp = new int[10001];
    static Object[] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        dp = new int[target + 1];

        SortedSet<Integer> tmp = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            tmp.add(Integer.parseInt(br.readLine()));
        }
        N = tmp.size();
        coins = tmp.toArray();

        int result = calculateNumberOfCoin();
        if (result == Integer.MAX_VALUE - 1) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }

    private static int calculateNumberOfCoin() {
        //init
        Arrays.fill(dp, Integer.MAX_VALUE - 1);
        dp[0] = 0;

        //calculate
        for (int i = 0; i < N; i++) {
            int coin = (int) coins[i];
            for (int j = coin; j <= target; j++) {
                dp[j] = Math.min(dp[j], dp[j - coin] + 1);
            }
        }
        return dp[target];
    }
}