import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] route;
    static long[] distance;
    static List<Node>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        route = new int[N + 1];
        distance = new long[N + 1];
        tree = new List[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            tree[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken());

        System.out.println(dijkstra(start, end));
        findRoute(1, end, start);
    }

    private static boolean findRoute(int level, int current, int target) {
         if (current == target) {
             System.out.println(level);
             System.out.print(current);
             return true;
         }

        if (findRoute(level + 1, route[current], target)) {
            System.out.print(" " + current);
            return true;
        }
        return false;
    }

    private static long dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.cost >= distance[end]) continue;
            
            for (Node next : tree[current.st]) {
                if (distance[next.st] > current.cost + next.cost) {
                    distance[next.st] = current.cost + next.cost;
                    pq.offer(new Node(next.st, distance[next.st]));
                    route[next.st] = current.st;
                }
            }
        }
        return distance[end];
    }

    static class Node implements Comparable<Node> {
        int st;
        long cost;

        public Node(int st, long cost) {
            this.st = st;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }
}
