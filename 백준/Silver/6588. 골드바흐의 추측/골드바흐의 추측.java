import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean[] nums = new boolean[1_000_001];
        nums[0] = nums[1] = true;
        for (int i = 2; i <= 1_000_000; i++) {
            if (!nums[i]) {
                for (int j = i * 2; j <= 1_000_000; j += i) {
                    nums[j] = true;
                }
            }
        }

        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (N != 0) {
            boolean status = false;

            for (int i = 2; i <= N / 2; i++) {
                if (!nums[i] && !nums[N - i]) {
                    sb.append(N).append(" = ").append(i).append(" + ").append(N - i).append("\n");
                    status = true;
                    break;
                }
            }

            if (!status) sb.append("Goldbach's conjecture is wrong.\n");
            N = Integer.parseInt(br.readLine());
        }
        System.out.println(sb);
    }
}
