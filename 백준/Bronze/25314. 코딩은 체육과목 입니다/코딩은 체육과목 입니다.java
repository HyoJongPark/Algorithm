import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        if (N <= 4) {
            System.out.println("long int");
            System.exit(0);
        } else {
            N -= 4;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("long int");
        int status = N % 4 == 0 ? 0 : 1;
        N = (N / 4) + status;
        for (int i = N; i > 0; i--) {
            sb.insert(0, "long ");
        }
        System.out.println(sb);
    }
}