import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            String str = br.readLine();
            int k = Integer.parseInt(br.readLine());

            if (k == 1) {
                sb.append("1 1\n");
                continue;
            }

            int[] alpha = new int[26];
            for (int j = 0; j < str.length(); j++) {
                alpha[str.charAt(j) - 'a']++;
            }

            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < str.length(); j++) {
                if (alpha[str.charAt(j) - 'a'] < k) continue;

                int count = 1;
                for (int l = j + 1; l < str.length(); l++) {
                    if (str.charAt(j) == str.charAt(l)) count++;
                    if (count == k) {
                        min = Math.min(min, l - j + 1);
                        max = Math.max(max, l - j + 1);
                        break;
                    }
                }
            }
            if (min == Integer.MAX_VALUE || max == Integer.MIN_VALUE) {
                sb.append("-1\n");
            } else {
                sb.append(String.format("%d %d\n", min, max));
            }
        }
        System.out.println(sb);
    }
}