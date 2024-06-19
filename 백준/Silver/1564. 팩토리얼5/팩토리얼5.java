import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        long result = 1, MOD = (long) 1e12;
        for (int i = 2; i <= N; i++) {
            result *= i;

            while (result % 10 == 0) {
                result /= 10;
            }
            result %= MOD;
        }

        System.out.printf("%05d", result % 100_000);
    }
}
