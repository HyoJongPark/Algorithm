import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            int command = Integer.parseInt(br.readLine());

            if (command == 0) {
                sb.append(pq.isEmpty() ? 0 : pq.poll()).append('\n');
            } else {
                pq.add(command);
            }
        }
        System.out.println(sb);
    }
}
