import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Integer[] nums = new Integer[N];

        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(nums, Comparator.reverseOrder());

        long result = 0;
        for (int i = 0; i < N; i++) {
            int crr = nums[i] - i;

            if (crr <= 0) {
                break;
            }
            result += crr;
        }
        System.out.println(result);
    }
}
