import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int[] parents;
    static Edge[] edges;
    static PriorityQueue<Node> nodes = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        edges = new Edge[N];
        parents = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(i, Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
            parents[i] = i;
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                nodes.offer(new Node(edges[i], edges[j]));
            }
        }

        double cost = 0, count = 0;
        while (!nodes.isEmpty()) {
            Node current = nodes.poll();

            if (union(current.start.edgeNo, current.end.edgeNo)) {
                cost += current.cost;

                if (++count == N - 1) {
                    break;
                }
            }
        }
        System.out.println(cost);
    }

    private static boolean union(int start, int end) {
        start = find(start);
        end = find(end);

        if (start == end) {
            return false;
        }
        parents[start] = end;
        return true;
    }

    private static int find(int edgeNo) {
        if (parents[edgeNo] == edgeNo) {
            return edgeNo;
        }
        return parents[edgeNo] = find(parents[edgeNo]);
    }

    static class Edge {
        int edgeNo;
        double x, y;

        public Edge(int edgeNo, double x, double y) {
            this.edgeNo = edgeNo;
            this.x = x;
            this.y = y;
        }
    }

    static class Node implements Comparable<Node> {
        Edge start, end;
        double cost;

        public Node(Edge start, Edge end) {
            this.start = start;
            this.end = end;
            this.cost = Math.sqrt(Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2));
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.cost, o.cost);
        }
    }
}
