import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int count = countNumberOfZero();
        System.out.println(count);
    }

    private static int countNumberOfZero() {
        int count = 0;
        while (N >= 5) {
            count += N / 5;
            N /= 5;
        }
        return count;
    }

}
