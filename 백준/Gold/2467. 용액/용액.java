import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0, right = N - 1;
        long result = Long.MAX_VALUE;
        int[] resultNums = new int[2];
        while (left < right) {
            long current = nums[left] + nums[right];

            if (result >= Math.abs(current)) {
                result = Math.abs(current);
                resultNums[0] = nums[left];
                resultNums[1] = nums[right];
            }

            if (current >= 0) {
                right--;
            } else {
                left++;
            }
        }

        System.out.printf("%d %d", resultNums[0], resultNums[1]);
    }
}