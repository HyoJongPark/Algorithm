import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

class Main {
    static int N;
    static PriorityQueue<Integer> q = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());

            if (num == 0 && !q.isEmpty()) sb.append(q.poll()).append("\n");
            else if (num == 0) sb.append(0).append("\n");
            else q.offer(num);
        }

        System.out.println(sb);
    }
}