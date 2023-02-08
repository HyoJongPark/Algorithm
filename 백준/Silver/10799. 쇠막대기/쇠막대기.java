import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] input = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();

        int result = 0;
        for (int i = 0; i < input.length; i++) {
            if (input[i] == '(') {
                stack.push('(');
                continue;
            }
            
            if (input[i] == ')') {
                stack.pop();
                if (input[i - 1] == '(') {
                    result += stack.size();
                } else {
                    result++;
                }
            }
        }

        System.out.println(result);
    }
}
