import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    static int N;
    static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        dp = new int[N];

        dp[0] = 1;
        int result = 1;
        for (int i = 1; i < N; i++) {
            int max = 0;

            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[i]) {
                    max = Math.max(max, dp[j]);
                }
            }

            dp[i] = max + 1;
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}
