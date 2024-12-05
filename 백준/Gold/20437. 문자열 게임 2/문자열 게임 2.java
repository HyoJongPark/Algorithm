import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String s = br.readLine();
            int length = Integer.parseInt(br.readLine());

            if (length == 1) {
                sb.append("1 1\n");
                continue;
            }

            int[] result = findResult(s, length);
            if (result[0] == Integer.MAX_VALUE || result[1] == Integer.MIN_VALUE) {
                sb.append("-1\n");
            } else {
                sb.append(result[0]).append(" ").append(result[1]).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static int[] findResult(String s, int length) {
        int[] count = new int[27];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] < length) {
                continue;
            }

            int currentCount = 1;
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    currentCount++;
                }

                if (currentCount == length) {
                    min = Math.min(min, j - i + 1);
                    max = Math.max(max, j - i + 1);
                    break;
                }
            }
        }
        return new int[]{min, max};
    }
}
