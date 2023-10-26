import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        if (N == 0) {
            sb.append(0);
        } else {
            findResult();
        }
        System.out.println(sb.reverse());
    }

    private static void findResult() {
        while (N != 1) {
            sb.append(Math.abs(N % -2));

            N = (int) (Math.ceil((double) N / -2));
        }
        sb.append(N);
    }
}
