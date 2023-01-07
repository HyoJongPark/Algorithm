import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] number = br.readLine().toCharArray();
        Arrays.sort(number);

        StringBuilder sb = new StringBuilder();
        if (number[number.length - 1] == '0') {
            System.out.println(0);
            System.exit(0);
        } else {
            for (int i = number.length - 1; i >= 0; i--) {
                sb.append(number[i] - '0');
            }
        }
        System.out.println(sb);
    }
}
