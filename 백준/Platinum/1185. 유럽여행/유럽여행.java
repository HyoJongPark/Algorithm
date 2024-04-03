import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static int N, P;
    static int[] costs;
    static List<Node>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        costs = new int[N + 1];
        nodes = new List[N + 1];

        int minCost = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            costs[i] = Integer.parseInt(br.readLine());
            nodes[i] = new ArrayList<>();
            minCost = Math.min(costs[i], minCost);
        }
        for (int i = 0; i < P; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = costs[a] + costs[b] + 2 * Integer.parseInt(st.nextToken());

            nodes[a].add(new Node(b, cost));
            nodes[b].add(new Node(a, cost));
        }

        System.out.println(mst(minCost));
    }

    private static int mst(int minCost) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[N + 1];

        int totalCost = minCost;
        pq.offer(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (check[current.en]) continue;

            check[current.en] = true;
            totalCost += current.cost;
            for (Node node : nodes[current.en]) {
                if (!check[node.en]) {
                    pq.offer(node);
                }
            }
        }
        return totalCost;
    }

    static class Node implements Comparable<Node> {
        int en;
        int cost;

        public Node(int en, int cost) {
            this.en = en;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
