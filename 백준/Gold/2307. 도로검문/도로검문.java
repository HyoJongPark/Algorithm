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

    static int N, M;
    static List<Node>[] nodes;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodes = new List[N + 1];

        for (int i = 0; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodes[a].add(new Node(b, c));
            nodes[b].add(new Node(a, c));
        }

        int[] history = new int[N + 1];
        int initCost = calculateInitCost(history);

        int result = Integer.MIN_VALUE;
        for (int i = 2; i <= N; i++) {
            result = Math.max(result, removeNodeAndCalculateCost(history[i], i));
        }

        System.out.println(result == INF ? -1 : result - initCost);
    }

    private static int removeNodeAndCalculateCost(int st, int en) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);

        distance[1] = 0;
        pq.offer(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (Node next : nodes[current.targetNo]) {
                if (current.targetNo == st && next.targetNo == en) {
                    continue;
                }

                if (distance[next.targetNo] > current.cost + next.cost) {
                    distance[next.targetNo] = current.cost + next.cost;
                    pq.offer(new Node(next.targetNo, distance[next.targetNo]));
                }
            }
        }

        return distance[N];
    }

    private static int calculateInitCost(int[] history) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);

        distance[1] = 0;
        pq.offer(new Node(1, 0));
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (Node next : nodes[current.targetNo]) {
                if (distance[next.targetNo] > current.cost + next.cost) {
                    distance[next.targetNo] = current.cost + next.cost;
                    history[next.targetNo] = current.targetNo;
                    pq.offer(new Node(next.targetNo, distance[next.targetNo]));
                }
            }
        }

        return distance[N];
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
