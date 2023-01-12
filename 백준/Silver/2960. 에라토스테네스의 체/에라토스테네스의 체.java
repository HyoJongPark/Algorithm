import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] nums = new int[N + 1];
        for (int i = 2; i < N + 1; i++) {
            nums[i] = i;
        }
        checkNotPrimeNumber(nums);
    }

    private static void checkNotPrimeNumber(int[] nums) {
        int cnt = 0;
        for (int i = 2; i <= N; i++) {
            if (nums[i] == 0) continue;
            cnt = printOrRemove(nums, cnt, i);
            for (int j = 2 * i; j < N + 1; j += i) {
                cnt = printOrRemove(nums, cnt, j);
            }
        }
    }

    private static int printOrRemove(int[] nums, int cnt, int i) {
        if (nums[i] == 0) return cnt;
        if (++cnt == K) {
            System.out.println(i);
            System.exit(0);
        }
        nums[i] = 0;
        return cnt;
    }
}