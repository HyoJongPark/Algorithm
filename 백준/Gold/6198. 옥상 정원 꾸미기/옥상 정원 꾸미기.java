import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {

    static int N;
    static int[] building;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        building = new int[N];

        for (int i = 0; i < N; i++) {
            building[i] = Integer.parseInt(br.readLine());
        }

        Stack<Integer> stack = new Stack<>();
        stack.push(building[0]);
        long count = 0;
        for (int crr = 1; crr < N; ) {
            if (stack.isEmpty()) {
                stack.push(building[crr]);
                crr++;
                continue;
            }

            int first = stack.peek();
            if (first <= building[crr]) {
                count += stack.size() - 1;
                stack.pop();
            } else {
                stack.push(building[crr++]);
            }
        }

        while (!stack.isEmpty()) {
            count += stack.size() - 1;
            stack.pop();
        }
        System.out.println(count);
    }
}
