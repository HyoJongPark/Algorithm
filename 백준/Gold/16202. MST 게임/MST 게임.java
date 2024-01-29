import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int N, M, K;
    static List<Node>[] nodes;
    static boolean[][] isRemoved;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        nodes = new List[N + 1];
        isRemoved = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nodes[a].add(new Node(a, b, i));
            nodes[b].add(new Node(b, a, i));
        }

        //prim
        StringBuilder sb = new StringBuilder();
        boolean canGetScore = true;
        while (K-- > 0) {
            if (!canGetScore) {
                sb.append("0 ");
                continue;
            }

            int cost = prim();
            if (cost == 0) {
                canGetScore = false;
            }
            sb.append(cost).append(" ");
        }
        System.out.println(sb);
    }

    private static int prim() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[N + 1];
        int cost = 0, count = 0;
        int minCost = Integer.MAX_VALUE;
        Node minNode = null;

        pq.offer(new Node(1, 1, 0));
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (check[current.en]) continue;

            check[current.en] = true;
            cost += current.cost;
            count++;
            if (current.en != 1 && current.cost < minCost) {
                minCost = current.cost;
                minNode = current;
            }
            for (Node next : nodes[current.en]) {
                if (!(isRemoved[next.en][next.st] || isRemoved[next.st][next.en]) && !check[next.en]) {
                    pq.offer(next);
                }
            }
        }

        if (count != N) return 0;
        isRemoved[minNode.st][minNode.en] = true;
        return cost;
    }

    static class Node implements Comparable<Node> {
        int st, en;
        int cost;

        public Node(int st, int en, int cost) {
            this.st = st;
            this.en = en;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
