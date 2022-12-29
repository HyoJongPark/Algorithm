import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int minimumLength = Integer.MAX_VALUE, total = 0;
        int start = 0, end = 0;
        while (start < N && end <= N) {
            if (total >= S && end - start < minimumLength) {
                minimumLength = end - start;
            }

            if (total >= S || end == N) {
                total -= nums[start++];
            } else {
                total += nums[end++];
            }
        }

        if (minimumLength == Integer.MAX_VALUE) {
            System.out.println("0");
        } else {
            System.out.println(minimumLength);
        }
    }
}