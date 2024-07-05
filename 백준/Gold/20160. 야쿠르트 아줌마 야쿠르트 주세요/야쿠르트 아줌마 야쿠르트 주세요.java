import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static int V, E;
    static int[] yakult;
    static List<Node>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        nodes = new List[V + 1];

        for (int i = 1; i <= V; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            nodes[a].add(new Node(b, c));
            nodes[b].add(new Node(a, c));
        }
        yakult = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int start = Integer.parseInt(br.readLine());
        long[] yakultDistance = moveYakult();
        int[] myDistance = dijkstra(start);

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            int current = yakult[i];
            if (yakultDistance[i] == Long.MAX_VALUE || myDistance[current] == Integer.MAX_VALUE) {
                continue;
            }

            if (yakultDistance[i] >= myDistance[current]) {
                answer = Math.min(answer, current);
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    private static long[] moveYakult() {
        long[] distance = new long[10];
        int index = 0;
        int beforeCity = yakult[index++];

        Arrays.fill(distance, Long.MAX_VALUE);
        distance[0] = 0;

        while (index < 10) {
            int[] dijkstra = dijkstra(beforeCity);
            int beforeIndex = index - 1;

            for (; index < 10; index++) {
                int nextCity = yakult[index];

                if (dijkstra[nextCity] != Integer.MAX_VALUE) {
                    distance[index++] = distance[beforeIndex] + dijkstra[nextCity];
                    beforeCity = nextCity;
                    break;
                }
            }

        }
        return distance;
    }

    private static int[] dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[V + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.cost > distance[current.target]) {
                continue;
            }

            for (Node next : nodes[current.target]) {
                if (distance[next.target] > current.cost + next.cost) {
                    distance[next.target] = current.cost + next.cost;
                    pq.offer(new Node(next.target, distance[next.target]));
                }
            }
        }
        return distance;
    }

    static class Node implements Comparable<Node> {

        int target, cost;

        public Node(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
