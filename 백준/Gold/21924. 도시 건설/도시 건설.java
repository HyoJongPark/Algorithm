import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static List<Node>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodes = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        long totalCost = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            totalCost += distance;
            nodes[a].add(new Node(b, distance));
            nodes[b].add(new Node(a, distance));
        }

        long result = mst();
        if (result == -1) {
            System.out.println(result);
        } else {
            System.out.println(totalCost - result);
        }
    }

    private static long mst() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[N + 1];

        pq.offer(new Node(1, 0));
        long distance = 0, count = 0;
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (check[current.nodeNo]) continue;

            count++;
            distance += current.distance;
            check[current.nodeNo] = true;
            for (Node next : nodes[current.nodeNo]) {
                if (check[next.nodeNo]) continue;

                pq.offer(next);
            }
        }

        if (count != N) return -1;
        return distance;
    }

    static class Node implements Comparable<Node> {
        int nodeNo, distance;

        public Node(int nodeNo, int distance) {
            this.nodeNo = nodeNo;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
}
