import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Stack;

public class Main {

    static int N;
    static int[] nums;
    static Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++) {
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                nums[stack.pop()] = nums[i];
            }
            stack.push(i);
        }

        while(!stack.isEmpty()) {
            nums[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) sb.append(nums[i]).append(' ');
        System.out.println(sb);
    }
}