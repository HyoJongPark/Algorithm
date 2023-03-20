import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int N, result = Integer.MIN_VALUE;
    static int[] nums;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        check = new boolean[N];
        for (int i = 0; i < N; i++) {
            check[i] = true;
            DFS(1, i, 0);
            check[i] = false;
        }
        System.out.println(result);
    }

    private static void DFS(int level, int current, int sum) {
        if (level == N) {
            result = Math.max(result, sum);
        }

        for (int next = 0; next < N; next++) {
            if (!check[next]) {
                check[next] = true;
                DFS(level + 1, next, sum + Math.abs(nums[current] - nums[next]));
                check[next] = false;
            }
        }
    }
}