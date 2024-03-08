import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int N, M, INF = 987654321;
    static int[] target;
    static List<Node>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        target = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(br.readLine());
        nodes = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nodes[a].add(new Node(b, cost));
            nodes[b].add(new Node(a, cost));
        }

        int[][] distance = new int[3][N + 1];
        dijkstra(0, distance);
        dijkstra(1, distance);
        dijkstra(2, distance);

        int cityNum = -1, max = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            if (i == target[0] || i == target[1] || i == target[2]) continue;

            int min = distance[0][i];
            min = Math.min(distance[1][i], min);
            min = Math.min(distance[2][i], min);

            if (min > max) {
                max = min;
                cityNum = i;
            }
        }
        System.out.println(cityNum);
    }

    private static void dijkstra(int targetIndex, int[][] distance) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(distance[targetIndex], INF);

        int startNode = target[targetIndex];
        pq.offer(new Node(startNode, 0));
        distance[targetIndex][startNode] = 0;
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (distance[targetIndex][current.nodeNo] < current.cost) continue;
            for (Node next : nodes[current.nodeNo]) {
                if (distance[targetIndex][next.nodeNo] > next.cost + current.cost) {
                    distance[targetIndex][next.nodeNo] = next.cost + current.cost;
                    pq.offer(new Node(next.nodeNo, distance[targetIndex][next.nodeNo]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int nodeNo, cost;

        public Node(int nodeNo, int cost) {
            this.nodeNo = nodeNo;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
