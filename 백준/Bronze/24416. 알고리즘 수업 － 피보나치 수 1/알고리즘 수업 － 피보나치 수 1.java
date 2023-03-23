import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int recursionCount = 0;
    static int dpCount = 0;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new int[n];
        fib(n);
        fibonacci(n);
        System.out.printf("%d %d", recursionCount, dpCount);
    }

    public static int fib(int n) {
        if(n == 1 || n == 2){
            recursionCount++;
            return 1;
        }
        return fib(n - 1) + fib(n - 2);
    }
    public static int fibonacci(int n) {
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < n; i++) {
            dpCount++;
            dp[i] = dp[i-2] + dp[i-1];
        }
        return dp[n-1];
    }
}