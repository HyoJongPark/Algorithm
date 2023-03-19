import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, E;
    static long distanceA, distanceB;
    static List<Node>[] tree;
    static final long INF = 12345678L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        //initializeTree
        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            tree[a].add(new Node(b, c));
            tree[b].add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        findRoot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        System.out.println(Math.min(distanceA, distanceB));
    }

    private static void findRoot(int a, int b) {
        dijkstra(1, new int[]{a, b}); // [1 -> A or B]
        dijkstra(a, new int[]{b}); //[A -> (B -> N)] or [B -> A]
        dijkstra(b, new int[]{N}); //[B -> N]
    }

    private static void dijkstra(int start, int[] target) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        long[] distance = new long[N + 1];
        Arrays.fill(distance, INF);

        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (Node next : tree[current.nodeNo]) {
                if (distance[next.nodeNo] > current.distance + next.distance) {
                    distance[next.nodeNo] = current.distance + next.distance;
                    pq.offer(new Node(next.nodeNo, current.distance + next.distance));
                }
            }
        }

        //(start -> end) 이동 불가할 때, -1
        if (distance[target[0]] == INF) {
            System.out.println(-1);
            System.exit(0);
        }

        if (target.length == 2) {
            distanceA = distance[target[0]];
            distanceB = distance[target[1]];
        } else if (target[0] == N) {
            distanceA += distance[target[0]];
        } else {
            distanceA += distance[target[0]];
            distanceB += distance[target[0]] + distance[N];
        }
    }

    static class Node implements Comparable<Node> {
        int nodeNo;
        int distance;

        public Node(int nodeNo, int distance) {
            this.nodeNo = nodeNo;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
}
