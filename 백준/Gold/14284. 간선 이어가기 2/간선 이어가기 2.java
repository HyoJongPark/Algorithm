import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static List<Node>[] nodes;

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodes = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodes[a].add(new Node(b, cost));
            nodes[b].add(new Node(a, cost));
        }

        st = new StringTokenizer(br.readLine());
        int result = dijkstra(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        System.out.println(result);
    }

    private static int dijkstra(int st, int en) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[N + 1];

        Arrays.fill(distance, INF);
        distance[st] = 0;
        pq.offer(new Node(st, 0));
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (distance[current.nodeNo] > current.cost) continue;
            for (Node next : nodes[current.nodeNo]) {
                if (distance[next.nodeNo] > current.cost + next.cost) {
                    distance[next.nodeNo] = current.cost + next.cost;
                    pq.offer(new Node(next.nodeNo, distance[next.nodeNo]));
                }
            }
        }
        return distance[en];
    }

    static class Node implements Comparable<Node> {
        int nodeNo, cost;

        public Node(int nodeNo, int cost) {
            this.nodeNo = nodeNo;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return 0;
        }
    }
}
