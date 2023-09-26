import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main {
    static int N;
    static int[] nums;
    static List<Integer> calNums = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(nums);
        for (int i = 0; i < N; i++) {
            for (int j = i; j < N; j++) {
                calNums.add(nums[i] + nums[j]);
            }
        }

        calNums.sort(Integer::compare);
        System.out.println(search());
    }

    private static int search() {
        for (int i = N - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                if (binarySearch(nums[i] - nums[j])) {
                    return nums[i];
                }
            }
        }
        return -1;
    }

    private static boolean binarySearch(int target) {
        int left = 0, right = calNums.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (calNums.get(mid) > target) {
                right = mid -1;
            } else if (calNums.get(mid) < target) {
                left = mid + 1;
            } else {
                return  true;
            }
        }
        return false;
    }
}
