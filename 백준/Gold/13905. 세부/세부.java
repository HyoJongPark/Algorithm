import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//크루스칼
class Main {
    static int N, M, start, end;
    static int[] parent;
    static PriorityQueue<Node> nodes = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            nodes.offer(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        System.out.println(kruskal());
    }

    private static int kruskal() {
        while (!nodes.isEmpty()) {
            Node current = nodes.poll();

            if (union(current.st, current.en)) {
                if (find(start) == find(end)) {
                    return current.cost;
                }
            }
        }
        return 0;
    }

    private static boolean union(int st, int en) {
        st = find(st);
        en = find(en);

        if (st == en) {
            return false;
        }
        parent[st] = en;
        return true;
    }

    private static int find(int nodeNo) {
        if (parent[nodeNo] == nodeNo) {
            return nodeNo;
        }

        parent[nodeNo] = find(parent[nodeNo]);
        return parent[nodeNo];
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
            return o.cost - this.cost;
        }
    }
}
