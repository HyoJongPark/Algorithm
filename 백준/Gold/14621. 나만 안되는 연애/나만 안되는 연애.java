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
    static char[] school;
    static List<Node>[] nodes;

    public static void main(String[] args) throws IOException {
        //initialize
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        school = new char[N + 1];
        nodes = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            school[i] = st.nextToken().toCharArray()[0];
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            nodes[a].add(new Node(b, distance));
            nodes[b].add(new Node(a, distance));
        }

        int result = mst();
        System.out.println(result);
    }

    private static int mst() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[N + 1];

        pq.offer(new Node(1, 0));
        int distance = 0, count = 0;
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (check[current.targetNode]) continue;
            if (count == N) break;

            count++;
            distance += current.distance;
            check[current.targetNode] = true;
            for (Node next : nodes[current.targetNode]) {
                if (school[current.targetNode] == school[next.targetNode] || check[next.targetNode]) continue;

                pq.offer(next);
            }
        }

        if (count != N) return -1;
        return distance;
    }

    static class Node implements Comparable<Node> {
        int targetNode, distance;

        public Node(int targetNode, int distance) {
            this.targetNode = targetNode;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
}
