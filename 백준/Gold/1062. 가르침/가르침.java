import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, K, result = 0;
    static String[] input;
    static boolean[] check = new boolean[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken()) - 5;
        input = new String[N];

        if (K < 0) {
            System.out.println("0");
            return;
        }

        for (int i = 0; i < N; i++) {
            input[i] = br.readLine();

            for (int j = 4; j < input[i].length() - 4; j++) {
                check[input[i].charAt(j) - 'a'] = true;
            }
        }
        check['a' - 'a'] = false;
        check['n' - 'a'] = false;
        check['t' - 'a'] = false;
        check['i' - 'a'] = false;
        check['c' - 'a'] = false;

        dfs(0, -1);
        System.out.println(result);
    }

    private static void dfs(int depth, int lastIdx) {
        if (depth == K) {
            countReadableInput();
            return;
        }

        boolean allCheck = true;
        for (int i = lastIdx + 1; i < 26; i++) {
            if (check[i]) {
                allCheck = false;
                check[i] = false;
                dfs(depth + 1, i);
                check[i] = true;
            }
        }

        if (allCheck) {
            countReadableInput();
        }
    }

    private static void countReadableInput() {
        int count = 0;

        for (int i = 0; i < N; i++) {
            String crr = input[i];

            if (canRead(crr)) {
                count++;
            }
        }
        result = Math.max(result, count);
    }

    private static boolean canRead(String crr) {
        for (int i = 4; i < crr.length() - 4; i++) {
            if (check[crr.charAt(i) - 'a']) {
                return false;
            }
        }
        return true;
    }
}
