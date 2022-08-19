import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String commands = br.readLine();
            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine(), "[,]");
            Deque<Integer> q = new ArrayDeque<>();
            while (st.hasMoreTokens()) {
                q.offer(Integer.parseInt(st.nextToken()));
            }

            boolean direction = true, error = false;
            for (int j = 0; j < commands.length(); j++) {
                String command = commands.substring(j, j + 1);
                if (command.equals("D") && q.isEmpty()) {
                    answer.append("error\n");
                    error = true;
                    break;
                }

                if (command.equals("R")) direction = !direction;
                else if (command.equals("D") && direction) q.pollFirst();
                else if (command.equals("D")) q.pollLast();
            }

            if (q.isEmpty() && !error) answer.append("[]").append("\n");
            else if (!q.isEmpty()) answer.append("[");

            while (!error && !q.isEmpty()) {
                if (direction) answer.append(q.pollFirst());
                else answer.append(q.pollLast());

                if (q.size() != 0) answer.append(",");
                else answer.append("]").append("\n");
            }
        }
        System.out.print(answer);
    }
}