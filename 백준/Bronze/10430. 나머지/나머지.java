import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken()), C = Integer.parseInt(st.nextToken());
        int[] result = new int[4];
        result[0] = (A + B) % C;
        result[1] = ((A % C) + (B % C)) % C;
        result[2] = (A * B) % C;
        result[3] = ((A % C) * (B % C)) % C;

        for (int i : result) {
            System.out.println(i);
        }
    }
}