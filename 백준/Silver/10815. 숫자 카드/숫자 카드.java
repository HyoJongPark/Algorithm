import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);

        int M = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine(), " ");
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < M; i++) {
            int find = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(nums, find)).append(" ");
        }
        System.out.println(sb);
    }

    public static int binarySearch(int[] nums, int find) {
        int left = 0;
        int right = nums.length - 1;
        int mid = right / 2;

        while (left <= right) {
            mid = (left + right) / 2;
            if (nums[mid] == find) {
                return 1;
            } else if (nums[mid] < find) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return 0;
    }
}
