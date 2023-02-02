import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N, M, K, S;
    static int safeCost, dangerCost;
    static long[] costs, check;
    static List<Integer>[] tree;
    static Queue<Node> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        costs = new long[N + 1];
        check = new long[N + 1];
        Arrays.fill(check, Long.MAX_VALUE);
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        safeCost = Integer.parseInt(st.nextToken());
        dangerCost = Integer.parseInt(st.nextToken());

        for (int i = 0; i < K; i++) {
            int dangerCity = Integer.parseInt(br.readLine());
            q.offer(new Node(dangerCity, 0));
            check[dangerCity] = 0;
            costs[dangerCity] = Long.MAX_VALUE;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        findDangerCity();
        System.out.println(calculateMovingCost());
    }

    private static void findDangerCity() {
        while (!q.isEmpty()) {
            Node current = q.poll();

            for (int next : tree[current.nodeNo]) {
                if (check[next] > current.distance + 1) {
                    check[next] = current.distance + 1;
                    q.offer(new Node(next, current.distance + 1));
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (costs[i] != Long.MAX_VALUE && check[i] <= S) costs[i] = dangerCost;
            else if (costs[i] != Long.MAX_VALUE) costs[i] = safeCost;
        }
    }

    private static long calculateMovingCost() {
        if (N == 1 || N == 2) return 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] movingCosts = new long[N + 1];
        Arrays.fill(movingCosts, Long.MAX_VALUE);
        pq.offer(new Node(1, 0));
        movingCosts[1] = 0;
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (int next : tree[current.nodeNo]) {
                if (costs[next] == Long.MAX_VALUE) continue;
                if (movingCosts[next] > current.distance + costs[next]) {
                    movingCosts[next] = current.distance + costs[next];
                    pq.offer(new Node(next, current.distance + costs[next]));
                }
            }
        }

        return movingCosts[N] - costs[N];
    }

    static class Node implements Comparable<Node> {
        int nodeNo;
        long distance;

        public Node(int nodeNo, long distance) {
            this.nodeNo = nodeNo;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.distance, o.distance);
        }
    }
}