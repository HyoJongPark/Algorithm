import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, target;
    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        int max = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, nums[i]);
        }
        target = Integer.parseInt(br.readLine());

        System.out.println(binarySearch(max));
    }

    private static int binarySearch(int max) {
        int left = 0, right = max, result = max;
        while (left <= right) {
            int mid = (left + right) / 2;
            int totalPrice = calculateTotalPrice(mid);

            if (totalPrice <= target) {
                left = mid + 1;
                result = mid;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    private static int calculateTotalPrice(int mid) {
        int totalPrice = 0;
        for (int num : nums) {
            totalPrice += Math.min(mid, num);
        }
        return totalPrice;
    }
}
