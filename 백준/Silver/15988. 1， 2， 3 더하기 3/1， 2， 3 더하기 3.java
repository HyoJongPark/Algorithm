import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{

    static int T;
    static long[] dp = new long[1_000_001];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i < 1_000_001; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % 1_000_000_009;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < T; i++){
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.print(sb);
    }
}
