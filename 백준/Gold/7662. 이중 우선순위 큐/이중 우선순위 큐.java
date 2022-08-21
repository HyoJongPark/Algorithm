import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static StringBuilder sb = new StringBuilder();
    static PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
    static PriorityQueue<Integer> min = new PriorityQueue<>();
    static HashMap<Integer, Integer> check = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            clearAll();

            for (int j = 0; j < N; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine(), " ");
                String command = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                if (command.equals("I")) {
                    max.offer(value);
                    min.offer(value);
                    check.put(value, check.getOrDefault(value, 0) + 1);
                } else if (command.equals("D")) {
                    if (check.isEmpty()) continue;
                    checkValue(value);
                }
            }

            if (check.isEmpty()) {
                sb.append("EMPTY").append("\n");
            }
            else {
                Integer value = checkValue(1);
                sb.append(value).append(" ");

                if (!check.isEmpty()) value = checkValue(-1);

                sb.append(value).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void clearAll() {
        max.clear();
        min.clear();
        check.clear();
    }

    private static Integer checkValue(int inputValue) {
        Integer value;
        while (true) {
            if (inputValue == -1) value = min.poll();
            else value = max.poll();

            int cnt = check.getOrDefault(value, 0);
            if (cnt == 0) continue;
            else if (cnt == 1) check.remove(value);
            else check.put(value, cnt - 1);

            break;
        }
        return value;
    }
}
