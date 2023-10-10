import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int N, M, K;
    static List<Node>[] tree;
    static long[][] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());


            tree[a].add(new Node(b, cost, 0));
            tree[b].add(new Node(a, cost, 0));
        }

        dijkstra();

        long result = Long.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            result = Math.min(distance[N][i], result);
        }
        System.out.println(result);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distance = new long[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(distance[i], Long.MAX_VALUE);
        }

        pq.offer(new Node(1, 0, 0));
        distance[1][0] = 0;
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (distance[current.nodeNo][current.count] < current.cost) continue;

            for (Node next : tree[current.nodeNo]) {
                if (current.count < K && distance[next.nodeNo][current.count + 1] > current.cost) {
                    distance[next.nodeNo][current.count + 1] = current.cost;
                    pq.offer(new Node(next.nodeNo, distance[next.nodeNo][current.count + 1], current.count + 1));
                }

                if (distance[next.nodeNo][current.count] > current.cost + next.cost) {
                    distance[next.nodeNo][current.count] = current.cost + next.cost;
                    pq.offer(new Node(next.nodeNo, distance[next.nodeNo][current.count], current.count));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int nodeNo, count;
        long cost;

        public Node(int nodeNo, long cost, int count) {
            this.nodeNo = nodeNo;
            this.cost = cost;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}
