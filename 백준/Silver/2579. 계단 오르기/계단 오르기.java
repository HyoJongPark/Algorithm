import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static int N;
    static Integer[] dp;
    static int[] values;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new Integer[N + 1];
        values = new int[N + 1];
        for(int i = 1; i <= N; i++) values[i] = Integer.parseInt(br.readLine());

        dp[0] = values[0];
        dp[1] = values[1];
        if(N >= 2) {
            dp[2] = values[1] + values[2];
        }

        System.out.println(find(N));
    }

    static int find(int crr) {
        if(dp[crr] == null) {
            dp[crr] = Math.max(find(crr - 2), find(crr - 3) + values[crr - 1]) + values[crr];
        }

        return dp[crr];
    }
}