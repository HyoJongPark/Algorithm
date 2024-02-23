import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    private static final long INF = 98765432100L;

    static int N, M;
    static int[] visible;
    static List<Node>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visible = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        nodes = new List[N];
        Arrays.fill(nodes, new ArrayList<>());

        for (int i = 0; i < N; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            nodes[a].add(new Node(b, distance));
            nodes[b].add(new Node(a, distance));
        }

        long result = dijkstra();
        System.out.println(result == INF ? -1 : result);
    }

    private static long dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] distance = new long[N];


        pq.offer(new Node(0, 0));
        Arrays.fill(distance, INF);
        distance[0] = 0;
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.distance > distance[current.nodeNo]) continue;
            for (Node next : nodes[current.nodeNo]) {
                if (next.nodeNo != N - 1 && visible[next.nodeNo] == 1) continue;

                if (distance[next.nodeNo] > next.distance + current.distance) {
                    distance[next.nodeNo] = next.distance + current.distance;
                    pq.offer(new Node(next.nodeNo, distance[next.nodeNo]));
                }
            }
        }
        return distance[N - 1];
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
