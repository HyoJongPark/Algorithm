import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K;
    static long[] distance;
    static List<Node>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        distance = new long[N + 1];
        tree = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            tree[v].add(new Node(u, d));
        }

        int nodeNo = 0;
        long resultDist = Long.MIN_VALUE;
        dijkstra(new StringTokenizer(br.readLine()));

        for (int i = 1; i <= N; i++) {
            if (distance[i] != Long.MAX_VALUE && distance[i] > resultDist) {
                resultDist = distance[i];
                nodeNo = i;
            }
        }
        System.out.printf("%d\n%d", nodeNo, resultDist);
    }

    private static void dijkstra(StringTokenizer st) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(distance, Long.MAX_VALUE);

        while (st.hasMoreTokens()) {
            int start = Integer.parseInt(st.nextToken());
            distance[start] = 0;
            pq.offer(new Node(start, 0));
        }

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.distance > distance[current.nodeNo]) continue;

            for (Node next : tree[current.nodeNo]) {
                if (distance[next.nodeNo] > current.distance + next.distance) {
                    distance[next.nodeNo] = current.distance + next.distance;
                    pq.offer(new Node(next.nodeNo, distance[next.nodeNo]));
                }
            }
        }
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