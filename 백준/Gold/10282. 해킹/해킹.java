import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    private static final int INF = 987654321;
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            List<Node>[] tree = new List[n + 1];
            for (int i = 1; i <= n; i++) {
                tree[i] = new ArrayList<>();
            }
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                tree[b].add(new Node(a, cost));
            }

            int[] distance = dijkstra(c, tree);
            int count = 0, maxCost = Integer.MIN_VALUE;
            for (int i = 1; i <= n; i++) {
                if (distance[i] == INF) continue;

                count++;
                maxCost = Math.max(maxCost, distance[i]);
            }
            sb.append(count).append(" ").append(maxCost).append("\n");
        }
        System.out.print(sb);
    }

    private static int[] dijkstra(int c, List<Node>[] tree) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[tree.length];

        Arrays.fill(distance, INF);
        pq.offer(new Node(c, 0));
        distance[c] = 0;
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.cost > distance[current.targetNo]) continue;

            for (Node next : tree[current.targetNo]) {
                if (distance[next.targetNo] > current.cost + next.cost) {
                    distance[next.targetNo] = current.cost + next.cost;
                    pq.offer(new Node(next.targetNo, distance[next.targetNo]));
                }
            }
        }
        return distance;
    }

    static class Node implements Comparable<Node> {
        int targetNo, cost;

        public Node(int targetNo, int cost) {
            this.targetNo = targetNo;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
