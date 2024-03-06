import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] inDegree;
    static List<Integer>[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inDegree = new int[N + 1];
        sequence = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            sequence[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sequence[a].add(b);
            inDegree[b]++;
        }

        String result = topologicalSort();
        System.out.println(result);
    }

    private static String topologicalSort() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) pq.offer(i);
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Integer current = pq.poll();

            if (inDegree[current] == 0) sb.append(current).append(" ");
            for (int next : sequence[current]) {
                if (--inDegree[next] == 0) {
                    pq.offer(next);
                }
            }
        }
        return sb.toString();
    }
}
