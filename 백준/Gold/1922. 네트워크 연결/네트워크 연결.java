import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] parentNode;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parentNode = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parentNode[i] = i;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            pq.offer(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int count = 0, cost = 0;
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (union(current.st, current.en)) {
                cost += current.cost;
                count++;

                if (count == N - 1) {
                    break;
                }
            }
        }
        System.out.println(cost);
    }

    private static boolean union(int st, int en) {
        st = find(st);
        en = find(en);

        if (st == en) return false;

        parentNode[st] = en;
        return true;
    }

    private static int find(int nodeNo) {
        if (parentNode[nodeNo] == nodeNo) {
            return nodeNo;
        }
        parentNode[nodeNo] = find(parentNode[nodeNo]);
        return parentNode[nodeNo];
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
