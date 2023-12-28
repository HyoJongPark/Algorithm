import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    private static final int MAX_VALUE = 10_000;

    static int T, N;
    static boolean[] nums = new boolean[MAX_VALUE + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        nums[0] = nums[1] = true;
        for (int i = 2; i <= MAX_VALUE; i++) {
            if (!nums[i]) {
                for (int j = i * 2; j <= MAX_VALUE; j += i) {
                    nums[j] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            int a = 0, b = 0;
            for (int i = N / 2; i >= 2; i--) {
                if (!nums[i] && !nums[N - i]) {
                    sb.append(i).append(" ").append(N - i).append("\n");
                    break;
                }
            }
        }
        System.out.print(sb);
    }
}
