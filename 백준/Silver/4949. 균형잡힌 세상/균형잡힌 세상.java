import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            char[] chars = br.readLine().toCharArray();
            if (chars.length == 1 && chars[0] == '.') break;

            if (isBalanced(chars)) sb.append("yes").append("\n");
            else sb.append("no").append("\n");
        }

        System.out.println(sb);
    }

    private static boolean isBalanced(char[] chars) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '[') {
                stack.push(']');
            } else if (chars[i] == '(') {
                stack.push(')');
            } else if (chars[i] == ']' || chars[i] == ')') {
                if (stack.isEmpty() || stack.pop() != chars[i]) return false;
            }
        }

        return stack.isEmpty();
    }
}