import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    static long N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Long.parseLong(br.readLine());

        System.out.println(N % 2 == 1 ? "SK" : "CY");
    }
}