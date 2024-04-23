import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {

    static int G;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        G = Integer.parseInt(br.readLine());

        List<Long> result = new ArrayList<>();
        long left = 1, right = 2;
        while (right <= 100_000) {
            long diff = (right * right) - (left * left);

            if (diff < G) {
                right++;
            } else {
                if (diff == G) {
                    result.add(right);
                }

                left++;
            }
        }

        StringBuilder sb = new StringBuilder();
        if (result.isEmpty()) {
            sb.append("-1");
        }
        for (Long i : result) {
            sb.append(i).append("\n");
        }
        System.out.print(sb);
    }
}
