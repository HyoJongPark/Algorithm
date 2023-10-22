import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int T, N;
    static PriorityQueue<Long> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            pq = new PriorityQueue<>();

            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                pq.offer(Long.parseLong(st.nextToken()));
            }

            sb.append(findMinCost()).append("\n");
        }
        System.out.print(sb);
    }

    private static long findMinCost() {
        long result = 0;
        while (pq.size() > 1) {
            long next = pq.poll() + pq.poll();

            result += next;
            pq.offer(next);
        }

        return result;
    }
}