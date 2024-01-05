import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int N;
    static boolean[] check;
    static List<Node>[] nodes;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        check = new boolean[N + 1];
        nodes = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                if (i != j) {
                    nodes[i].add(new Node(j, Integer.parseInt(st.nextToken())));
                } else {
                    st.nextToken();
                }
            }
        }

        pq.offer(new Node(1, 0));
        long cost = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (!check[cur.en]) {
                check[cur.en] = true;
                cost += cur.cost;

                for (Node next : nodes[cur.en]) {
                    if (!check[next.en]) {
                        pq.offer(next);
                    }
                }
            }
        }
        System.out.println(cost);
    }

    static class Node implements Comparable<Node> {
        int en;
        long cost;

        public Node(int en, long cost) {
            this.en = en;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}
