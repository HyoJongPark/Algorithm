import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static long N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Long.parseLong((br.readLine()));
        System.out.println(binarySearch());
    }

    private static long binarySearch() {
        long left = 0, right = N;
        long result = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (Math.pow(mid, 2) >= N) {
                right = mid - 1;
                result = mid;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }
}
