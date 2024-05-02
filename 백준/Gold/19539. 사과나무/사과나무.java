import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int sum = 0, oneCount = 0, twoCount = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int crr = Integer.parseInt(st.nextToken());

            oneCount += crr % 2;
            twoCount += crr / 2;
            sum += crr;
        }

        if (sum % 3 != 0 || oneCount > twoCount) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }
}