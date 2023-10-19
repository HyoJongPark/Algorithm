import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N, M, start, end, C, max = Integer.MIN_VALUE;
    static List<Node>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), cost = Integer.parseInt(st.nextToken());
            tree[a].add(new Node(b, cost));
            tree[b].add(new Node(a, cost));
            max = Math.max(max, cost);
        }

        System.out.println(binarySearch());
    }

    private static int binarySearch() {
        int left = 0, right = max, result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (dijkstra(mid)) {
                right = mid - 1;
                result = mid;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    private static boolean dijkstra(int maxCost) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[N + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.cost > distance[current.nodeNo]) continue;

            for (Node next : tree[current.nodeNo]) {
                if (distance[next.nodeNo] > current.cost + next.cost && next.cost <= maxCost) {
                    distance[next.nodeNo] = current.cost + next.cost;
                    pq.offer(new Node(next.nodeNo, distance[next.nodeNo]));
                }
            }
        }

        return distance[end] <= C;
    }

    static class Node implements Comparable<Node> {
        int nodeNo;
        int cost;

        public Node(int target, int cost) {
            this.nodeNo = target;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
