import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int red, green;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        red = Integer.parseInt(st.nextToken());
        green = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();
        int gcd = gcd(red, green);
        for (int i = 1; i <= gcd; i++) {
            if (gcd % i == 0) {
                sb.append(String.format("%d %d %d\n", i, red / i, green / i));
            }
        }
        System.out.print(sb);
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }
}
