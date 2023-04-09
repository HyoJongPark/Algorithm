import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        long left = 1, right = K;
        while (left < right) {
            long mid = (left + right) / 2; //target
            long count = 0;

            for (int i = 1; i <= N; i++) {
                if (mid / i == 0) break;
                count += Math.min(mid / i, N);
            }

            if (K <= count) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }
}