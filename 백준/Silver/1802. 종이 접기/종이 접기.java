import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            String current = br.readLine();

            if (current.length() == 1) {
                sb.append("YES\n");
                continue;
            }

            int len = current.length(), idx = current.length() / 2;
            boolean status = false;
            while (idx != 0) {
                for (int i = 0, j = len - 1; i < idx; i++, j--) {
                    if (current.charAt(i) == current.charAt(j)) {
                        status = true;
                        break;
                    }
                }

                if (status) break;
                len /= 2;
                idx /= 2;
            }

            if (status) {
                sb.append("NO\n");
            } else {
                sb.append("YES\n");
            }
        }
        System.out.print(sb);
    }
}
