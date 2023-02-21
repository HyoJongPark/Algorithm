import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Deque<String> deque = new ArrayDeque<>();
        boolean[] appendStatus = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("1")) {
                appendStatus[deque.size()] = true;
                deque.addLast(st.nextToken());
            } else if (command.equals("2")) {
                appendStatus[deque.size()] = false;
                deque.addFirst(st.nextToken());
            } else {
                if (deque.isEmpty()) continue;
                
                if (appendStatus[deque.size() - 1]) {
                    deque.pollLast();
                } else {
                    deque.pollFirst();
                } 
            }
        }

        if (deque.isEmpty()) {
            System.out.println(0);
        } else {
            String result = deque.stream()
                    .collect(Collectors.joining());
            System.out.println(result);
        }
    }
}