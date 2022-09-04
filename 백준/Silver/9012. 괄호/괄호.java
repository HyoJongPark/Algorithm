import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            char[] chars = br.readLine().toCharArray();
            Queue<Character> q = new LinkedList<>();

            int cnt = 0;
            while (cnt < chars.length) {
                if (q.isEmpty() && chars[cnt] == ')') break;

                if (chars[cnt] == '(') q.offer('(');
                else q.poll();

                cnt++;
            }
            if (q.isEmpty() && cnt == chars.length) sb.append("YES").append("\n");
            else sb.append("NO").append("\n");
        }

        System.out.println(sb);
    }
}