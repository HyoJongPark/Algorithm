import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Main {
    static int N;
    static int[] nums;
    static Set<Integer> check = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int st = 0, en = 0;
        long count = 0;
        check.add(nums[0]);
        while (st < N) {
            while (en < N - 1 && !check.contains(nums[en + 1])) {
                check.add(nums[++en]);
            }

            count += en - st + 1;
            check.remove(nums[st++]);
        }
        System.out.println(count);
    }
}
