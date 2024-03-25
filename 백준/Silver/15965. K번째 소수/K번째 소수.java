import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        boolean[] isPrime = new boolean[8_000_000];
        Arrays.fill(isPrime, true);
        isPrime[2] = true;

        for (int i = 2; i < Math.sqrt(isPrime.length); i++) {
            if (!isPrime[i]) continue;

            for (int j = i * i; j < isPrime.length; j += i) {
                isPrime[j] = false;
            }
        }

        int count = 0;
        for (int i = 2; i < isPrime.length; i++) {
            if (isPrime[i]) count++;

            if (count == K) {
                System.out.println(i);
                System.exit(0);
            }
        }
    }
}
