import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

class Main {
    static int T, N;
    static String[] keys;
    static Set<String> set;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            keys = new String[N];
            set = new HashSet<>();
            for (int i = 0; i < N; i++) {
                String phoneNumber = br.readLine();
                set.add(phoneNumber);
                keys[i] = phoneNumber;
            }

            if (findResult()) {
                sb.append("NO\n");
            } else {
                sb.append("YES\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean findResult() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < keys[i].length(); j++) {
                if (set.contains(keys[i].substring(0, j))) {
                    return true;
                }
            }
        }
        return false;
    }
}
