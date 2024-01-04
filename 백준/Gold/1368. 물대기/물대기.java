import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[] parents;
    static PriorityQueue<Node> nodes = new PriorityQueue<Node>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
            nodes.offer(new Node(0, i, Integer.parseInt(br.readLine())));
        }
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                if (i != j) nodes.offer(new Node(i, j, Integer.parseInt(st.nextToken())));
                else st.nextToken();
            }
        }

        long cost = 0, count = 0;
        while (!nodes.isEmpty()) {
            Node current = nodes.poll();

            if (union(current.st, current.en)) {
                cost += current.cost;

                if (++count == N) {
                    break;
                }
            }
        }
        System.out.println(cost);
    }

    private static boolean union(int st, int en) {
        st = find(st);
        en = find(en);

        if (st == en) {
            return false;
        }
        parents[st] = en;
        return true;
    }

    private static int find(int nodeNo) {
        if (parents[nodeNo] == nodeNo) {
            return nodeNo;
        }
        parents[nodeNo] = find(parents[nodeNo]);
        return parents[nodeNo];
    }

    static class Node implements Comparable<Node> {
        int st, en;
        long cost;

        public Node(int st, int en, long cost) {
            this.st = st;
            this.en = en;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}
