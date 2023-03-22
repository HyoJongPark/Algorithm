import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BigInteger A = new BigInteger(br.readLine()), B = new BigInteger(br.readLine());

        System.out.printf("%d\n%d\n%d", A.add(B), A.subtract(B), A.multiply(B));
    }
}