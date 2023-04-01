import java.io.*;
import java.util.*;
import java.util.List;

public class Main {

    static String str;
    static boolean[] check;
    static List<Bracket> bracketPosition = new ArrayList<>();
    static TreeSet<String> results = new TreeSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        check = new boolean[str.length()];

        findBrackets();
        makeResult(0, 0);

        StringBuilder sb = new StringBuilder();
        for (String result : results) {
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static void findBrackets() {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack.push(i);
            } else if (str.charAt(i) == ')') {
                bracketPosition.add(new Bracket(stack.pop(), i));
            }
        }
    }

    private static void makeResult(int level, int idx) {
        if (level == bracketPosition.size() || idx == bracketPosition.size()) {
            return;
        }

        for (int i = idx; i < bracketPosition.size(); i++) {
            Bracket bracket = bracketPosition.get(i);

            check[bracket.start] = true;
            check[bracket.end] = true;
            results.add(makeString());
            makeResult(level + 1, i + 1);
            check[bracket.start] = false;
            check[bracket.end] = false;
        }
    }

    private static String makeString() {
        StringBuilder next = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (!check[i]) {
                next.append(str.charAt(i));
            }
        }
        return next.toString();
    }

    static class Bracket {
        int start, end;

        public Bracket(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
