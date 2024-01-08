import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int M, N;
    static int[] parents;
    static PriorityQueue<Node> nodes = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        while ((M = Integer.parseInt(st.nextToken())) != 0 && (N = Integer.parseInt(st.nextToken())) != 0) {
            parents = new int[N + 1];
            for (int i = 1; i <= M; i++) {
                parents[i] = i;
            }

            int cost = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int z = Integer.parseInt(st.nextToken());

                nodes.offer(new Node(x, y, z));
                nodes.offer(new Node(y, x, z));
                cost += z;
            }

            int count = 0;
            while (!nodes.isEmpty()) {
                Node current = nodes.poll();

                if (union(current.st, current.en)) {
                    cost -= current.cost;

                    if (++count == M) break;
                }
            }
            sb.append(cost).append("\n");
            st = new StringTokenizer(br.readLine());
        }
        System.out.println(sb);
    }

    private static boolean union(int st, int en) {
        st = find(st);
        en = find(en);

        if (st == en) {
            return false;
        }
        parents[st] = en;
        return true;
    }

    private static int find(int nodeNo) {
        if (parents[nodeNo] == nodeNo) {
            return nodeNo;
        }

        parents[nodeNo] = find(parents[nodeNo]);
        return parents[nodeNo];
    }

    static class Node implements Comparable<Node> {
        int st, en, cost;

        public Node(int st, int en, int cost) {
            this.st = st;
            this.en = en;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
