import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static final int MOD = 1_000_000_007;
    static long A, X;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        A = Long.parseLong(br.readLine()) % MOD;
        X = Long.parseLong(br.readLine());

        long result = 1;
        while (X > 0) {
            if ((X & 1) == 1) {
                result = (result * A) % MOD;
            }

            X >>= 1;
            A = (A * A) % MOD;
        }
        System.out.println(result);
    }
}
