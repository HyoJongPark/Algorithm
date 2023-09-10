import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static long N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());

        long result = binarySearch(1, N);
        System.out.println(result);
    }

    private static long binarySearch(long left, long right) {
        long result = Long.MIN_VALUE;
        while (left <= right) {
            long mid = (left + right) / 2;
            long sum = getSum(mid);

            if (sum > N) {
                right = mid - 1;
            } else {
                result = Math.max(result, mid);
                left = mid + 1;
            }
        }
        return result;
    }

    private static long getSum(long mid) {
        return (mid * (mid + 1)) / 2;
    }
}
