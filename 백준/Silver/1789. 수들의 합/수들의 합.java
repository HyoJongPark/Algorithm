import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static long N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());

        long result = Long.MIN_VALUE;
        long left = 1, right = N;

        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = (mid * (mid + 1)) / 2;

            if (sum > N) {
                right = mid - 1;
            } else {
                result = Math.max(result, mid);
                left = mid + 1;
            }
        }
        System.out.println(result);
    }
}
