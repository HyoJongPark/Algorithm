import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] tree;
    static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        tree = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = i;
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        edges.sort(Edge::compareTo);

        int totalDistance = 0, count = 0;
        for (Edge edge : edges) {
            if (union(edge.start, edge.end)) {
                totalDistance += edge.distance;
                count++;
            }

            if (count == N - 1) {
                System.out.println(totalDistance);
                System.exit(0);
            }
        }
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return false;
        }

        tree[a] = b;
        return true;
    }

    private static int find(int a) {
        if (a != tree[a]) {
            tree[a] = find(tree[a]);
        }
        return tree[a];
    }

    private static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int distance;

        public Edge(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance - o.distance;
        }
    }
}