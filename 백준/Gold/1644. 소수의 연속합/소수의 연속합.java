import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
    static int N;
    static boolean[] nums;
    static List<Integer> prime = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new boolean[N + 1];

        nums[0] = nums[1] = true;
        for (int i = 2; i <= N; i++) {
            if (!nums[i]) {
                prime.add(i);

                for (int j = i * 2; j <= N; j += i) {
                    nums[j] = true;
                }
            }
        }

        int st = 0, en = 0, sum = 2;
        int count = 0;
        while (st < prime.size()) {
            if (sum == N) {
                count++;
                sum -= prime.get(st++);
            } else if (sum > N) {
                sum -= prime.get(st++);
            } else {
                if (++en >= prime.size()) break;
                sum += prime.get(en);
            }
        }
        System.out.println(count);
    }
}
