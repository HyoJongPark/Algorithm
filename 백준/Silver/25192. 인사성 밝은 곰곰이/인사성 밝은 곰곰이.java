import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()) - 1;

        String input = br.readLine();
        Set<String> check = new HashSet<>();
        int result = 0;
        while (N-- > 0) {
            input = br.readLine();

            if (input.equals("ENTER")) {
                check = new HashSet<>();
            } else if (!check.contains(input)) {
                check.add(input);
                result++;
            }
        }
        System.out.println(result);
    }
}
