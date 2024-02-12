import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, Q;
    static int[] music, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        music = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[N];

        for (int i = 1; i < N; i++) {
            dp[i] = dp[i - 1];

            if (music[i] < music[i - 1]) {
                dp[i]++;
            }
        }

        //calculate result
        Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());

            int result = dp[y - 1] - dp[x - 1];
            if (result == 0) {
                sb.append("0\n");
            } else {
                sb.append(result).append("\n");
            }
        }
        System.out.println(sb);
    }
}
