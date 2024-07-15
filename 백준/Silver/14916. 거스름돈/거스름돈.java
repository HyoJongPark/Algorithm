import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int count = 0;
        while (N > 0) {
            if (N % 5 == 0) {
                count += N / 5;
                break;
            }

            N -= 2;
            count++;
        }

        if (N >= 0) {
            System.out.println(count);
        } else {
            System.out.println(-1);
        }
    }
}
