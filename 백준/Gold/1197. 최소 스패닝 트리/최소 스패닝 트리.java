import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int V, E;
    static int[] parents;
    static List<Node> nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parents = new int[V + 1];
        nodes = new ArrayList<>();

        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            nodes.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        nodes.sort(Node::compareTo);

        int cost = 0, count = 0;
        for (Node node : nodes) {
            if (union(node.st, node.en)) {
                cost += node.cost;
                count++;

                if (count == E - 1) {
                    break;
                }
            }
        }
        System.out.println(cost);
    }

    private static boolean union(int st, int en) {
        st = findSet(st);
        en = findSet(en);

        if (st == en) {
            return false;
        }
        parents[st] = en;
        return true;
    }

    private static int findSet(int nodeNo) {
        if (parents[nodeNo] == nodeNo) {
            return nodeNo;
        }
        parents[nodeNo] = findSet(parents[nodeNo]);
        return parents[nodeNo];
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
