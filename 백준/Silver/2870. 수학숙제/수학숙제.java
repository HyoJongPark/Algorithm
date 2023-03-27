import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<BigInteger> result = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            int length = str.length();

            for (int j = 0; j < length; j++) {
                StringBuilder sb = new StringBuilder();
                while (j < length && Character.isDigit(str.charAt(j))) {
                    sb.append(str.charAt(j));
                    j++;
                }

                if (sb.length() != 0) {
                    result.add(new BigInteger(sb.toString()));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        result.stream()
                .sorted()
                .forEach(num -> sb.append(num).append("\n"));
        System.out.println(sb);
    }
}