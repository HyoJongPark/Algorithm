import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    
    static final int MAX_VALUE = 1_000_000;

    static int T, N;
    static boolean[] nums = new boolean[MAX_VALUE + 1];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        //소수 찾기
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

            int count = 0;
            for (int i = 2; i <= N / 2; i++) {
                if (!nums[i] && !nums[N - i]) {
                    count++;
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}
