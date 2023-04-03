import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] input = new String[N];
        for (int i = 0; i < N; i++) {
            input[i] = br.readLine();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input[0].length(); i++) {
            char c = input[0].charAt(i);
            boolean status = true;
            for (int j = 1; j < N; j++) {
                if (c != input[j].charAt(i)) {
                    status = false;
                    break;
                }
            }

            if (status) {
                sb.append(c);
            } else {
                sb.append('?');
            }
        }
        System.out.println(sb);
    }

}