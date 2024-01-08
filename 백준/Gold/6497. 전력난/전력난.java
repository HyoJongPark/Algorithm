import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int M, N;
    static boolean[] check;
    static List<Node>[] nodes;
    static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        while ((M = Integer.parseInt(st.nextToken())) != 0 && (N = Integer.parseInt(st.nextToken())) != 0) {
            check = new boolean[M];
            nodes = new List[M];
            pq = new PriorityQueue<>();
            for (int i = 0; i < M; i++) {
                nodes[i] = new ArrayList<>();
            }

            int cost = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                nodes[x].add(new Node(y, z));
                nodes[y].add(new Node(x, z));
                cost += z;
            }

            pq.offer(new Node(0, 0));
            int count = 0;
            while (!pq.isEmpty()) {
                Node current = pq.poll();
                if (check[current.en]) continue;
                if (count == M - 1) {
                    sb.append(cost - current.cost).append("\n");
                    break;
                }

                count++;
                cost -= current.cost;
                check[current.en] = true;
                for (Node next : nodes[current.en]) {
                    if (check[next.en]) continue;

                    pq.offer(next);
                }
            }
            st = new StringTokenizer(br.readLine());
        }
        System.out.println(sb);
    }

    static class Node implements Comparable<Node> {
        int en, cost;

        public Node(int en, int cost) {
            this.en = en;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
