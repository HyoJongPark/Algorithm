import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < N; i++) {
            int left = 0, right = N - 1, target = nums[i];

            while (left < right) {
                if (left == i) {
                    left++;
                } else if (right == i) {
                    right--;
                }

                if (left >= right) {
                    break;
                }

                int sum = nums[left] + nums[right];
                if (sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    count++;
                    break;
                }
            }
        }
        System.out.println(count);
    }
}
