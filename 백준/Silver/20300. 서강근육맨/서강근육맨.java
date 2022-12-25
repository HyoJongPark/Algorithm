import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] nums = Arrays.stream(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .sorted()
                .toArray();

        int left = 0, right = N % 2 == 0 ? N - 1 : N - 2;
        long answer = nums[N - 1];
        for (int i = 0; i < N / 2; i++) {
            answer = Math.max(answer, nums[left + i] + nums[right - i]);
        }
        System.out.println(answer);
    }
}