import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String bombStr = br.readLine();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));

            if (stack.size() >= bombStr.length()) {
                boolean isNotEqual = check(bombStr, stack);
                if (!isNotEqual) {
                    for (int j = 0; j < bombStr.length(); j++) stack.pop();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.pop());
        System.out.println(sb.length() != 0 ? sb.reverse() : "FRULA");
    }

    private static boolean check(String boom, Stack<Character> stack) {
        boolean isNotEqual = false;
        for (int j = 0; j < boom.length(); j++) {
            if (stack.get(stack.size() - boom.length() + j) != boom.charAt(j)) {
                isNotEqual = true;
                break;
            }
        }
        return isNotEqual;
    }
}