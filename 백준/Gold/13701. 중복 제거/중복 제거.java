import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        HashSet<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            String next = st.nextToken();

            if (!set.contains(next)) {
                set.add(next);
                sb.append(next).append(" ");
            }
        }
        System.out.println(sb);
    }
}
