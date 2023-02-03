import java.io.IOException;
import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BigInteger N = sc.nextBigInteger(), M = sc.nextBigInteger();

        System.out.println(N.divide(M));
        System.out.println(N.remainder(M));
    }
}