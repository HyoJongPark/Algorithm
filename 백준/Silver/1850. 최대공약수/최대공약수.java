import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static long A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        long gcd = gcd(A, B);

        StringBuilder sb = new StringBuilder();
        for (long i = 0; i < gcd; i++) {
            sb.append(1);
        }
        System.out.print(sb);
    }

    private static long gcd(long a, long b) {
        if (b == 0) return a;

        return gcd(b, a % b);
    }
}
