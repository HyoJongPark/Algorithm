import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int T, R, C;
    static Edge[][] parents;
    static PriorityQueue<Node> nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            parents = new Edge[R][C];
            nodes = new PriorityQueue<>();

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    parents[i][j] = new Edge(i, j);
                }
            }

            for (int i = 0; i < R; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C - 1; j++) {
                    nodes.add(new Node(new Edge(i, j), new Edge(i, j + 1), Integer.parseInt(st.nextToken())));
                }
            }
            for (int i = 0; i < R - 1; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < C; j++) {
                    nodes.add(new Node(new Edge(i, j), new Edge(i + 1, j), Integer.parseInt(st.nextToken())));
                }
            }

            int cost = 0, count = 0;
            while (!nodes.isEmpty()) {
                Node current = nodes.poll();

                if (union(current.start, current.end)) {
                    cost += current.cost;

                    if (++count == (R * C) - 1) {
                        break;
                    }
                }
            }
            sb.append(cost).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean union(Edge start, Edge end) {
        start = find(start);
        end = find(end);

        if (start.equals(end)) {
            return false;
        }

        parents[start.x][start.y] = end;
        return true;
    }

    private static Edge find(Edge edge) {
        if (parents[edge.x][edge.y].equals(edge)) {
            return edge;
        }
        return parents[edge.x][edge.y] = find(parents[edge.x][edge.y]);
    }

    static class Edge {
        int x, y;

        public Edge(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            Edge edge = (Edge) o;
            return x == edge.x && y == edge.y;
        }
    }

    static class Node implements Comparable<Node> {
        Edge start, end;
        int cost;

        public Node(Edge start, Edge end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
