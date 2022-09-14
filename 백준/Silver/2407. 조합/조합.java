import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;


public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tmp = br.readLine().split(" ");
        N = Integer.parseInt(tmp[0]);
        M = Integer.parseInt(tmp[1]);

        BigInteger n1 = BigInteger.ONE;
        BigInteger n2 = BigInteger.ONE;

        for (int i = 0; i < M; i++) {
            n1 = n1.multiply(new BigInteger(String.valueOf(N - i)));
            n2 = n2.multiply(new BigInteger(String.valueOf(i + 1)));
        }

        BigInteger answer = n1.divide(n2);
        System.out.println(answer);
    }
}