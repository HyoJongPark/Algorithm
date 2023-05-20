import java.io.*;

public class Main {

    static int N;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(2);
            System.exit(0);
        }

        while (!(isPalindrome(N) && isPrime(N))) {
            N++;
        }
        System.out.println(N);
    }

    public static boolean isPrime(int x) {
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(int x) {
        String strX = Integer.toString(x);
        int cnt = strX.length();
        for (int i = 0; i <= cnt / 2; i++) {
            if (strX.charAt(i) != strX.charAt(cnt - i - 1)) {
                return false;
            }
        }
        return true;
    }
}