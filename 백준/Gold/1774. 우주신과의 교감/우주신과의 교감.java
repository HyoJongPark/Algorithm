import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] parents;
    static int[][] position;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        position = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            parents[i] = i;
            position[i] = new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
        }

        int count = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 1; i < N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (find(i) == find(j)) continue;

                double distance = Math.sqrt(Math.pow(position[i][0] - position[j][0], 2) + Math.pow(position[i][1] - position[j][1], 2));
                pq.offer(new Node(i, j, distance));
            }
        }

        double totalCost = 0;
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (union(current.st, current.en)) {
                totalCost += current.cost;
            }
        }
        System.out.printf("%.2f", totalCost);
    }

    private static boolean union(int st, int en) {
        st = find(st);
        en = find(en);

        if (st == en) {
            return false;
        } else if (st > en) {
            int tmp = st;
            st = en;
            en = tmp;
        }

        parents[st] = en;
        return true;
    }

    private static int find(int nodeNo) {
        if (parents[nodeNo] == nodeNo) {
            return nodeNo;
        }
        return parents[nodeNo] = find(parents[nodeNo]);
    }

    static class Node implements Comparable<Node> {
        int st, en;
        double cost;

        public Node(int st, int en, double cost) {
            this.st = st;
            this.en = en;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Double.compare(this.cost, o.cost);
        }
    }
}