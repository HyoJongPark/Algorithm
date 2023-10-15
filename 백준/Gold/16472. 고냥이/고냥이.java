import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static String str;
    static int[] alpha = new int[26];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        str = br.readLine();

        int st = 0, en = 0, count = 1, result = 0;
        alpha[str.charAt(0) - 'a'] += 1;
        while (en < str.length() - 1) {
            if (alpha[str.charAt(++en) - 'a']++ == 0) {
                count++;
            }

            if (count > N) {
                result = Math.max(result, en - st);
            }
            while (count > N && st < en) {
                if (--alpha[str.charAt(st++) - 'a'] == 0) {
                    count--;
                }
            }
        }

        result = result == 0 ? str.length() : result;
        System.out.println(result);
    }
}