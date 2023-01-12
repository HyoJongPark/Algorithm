import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int N, answer = Integer.MIN_VALUE, popNumber;
    static int[] nums, lGcd, rGcd;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        lGcd = new int[N];
        rGcd = new int[N];
        lGcd[0] = nums[0];
        rGcd[N - 1] = nums[N - 1];
        for (int i = 1; i < N; i++) {
            lGcd[i] = gcd(lGcd[i - 1], nums[i]);
        }
        for (int i = N - 2; i >= 0; i--) {
            rGcd[i] = gcd(rGcd[i + 1], nums[i]);
        }

        for (int i = 0; i < N; i++){
            int gcd;
            if (i == 0) {
                gcd = rGcd[1];
            } else if (i == N - 1) {
                gcd = lGcd[N - 2];
            } else {
                gcd = gcd(lGcd[i - 1], rGcd[i + 1]);
            }

            if (nums[i] % gcd != 0 && gcd > answer) {
                answer = gcd;
                popNumber = nums[i];
            }
        }

        if (answer == Integer.MIN_VALUE) {
            System.out.println(-1);
        } else {
            System.out.printf("%d %d", answer, popNumber);
        }
    }

    private static int calculateGcd(int skipNumber) {
        int gcd, startNumber;
        if (skipNumber == 0) {
            gcd = gcd(nums[1], nums[2]);
            startNumber = 3;
        } else if (skipNumber == 1) {
            gcd = gcd(nums[0], nums[2]);
            startNumber = 3;
        } else {
            gcd = gcd(nums[0], nums[1]);
            startNumber = 2;
        }

        for (; startNumber < N; startNumber++) {
            gcd = gcd(gcd, nums[startNumber]);
        }
        return gcd;
    }

    private static int gcd(int a, int b) {
        if (a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
}