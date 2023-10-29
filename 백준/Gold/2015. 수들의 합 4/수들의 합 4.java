import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static long result = 0;
    static int[] nums;
    static Map<Integer, Long> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nums = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            nums[i] = nums[i - 1] + Integer.parseInt(st.nextToken());

            if (nums[i] == K) {
                result++;
            }
            if (map.containsKey(nums[i] - K)) {
                result += map.get(nums[i] - K);
            }
            map.put(nums[i], map.getOrDefault(nums[i], 0L) + 1);
        }
        System.out.println(result);
    }
}