import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int V, E;
    static int[] parents;
    static PriorityQueue<int[]> nodes = new PriorityQueue<>((a, b) -> a[2] - b[2]);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parents = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            nodes.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        int cost = 0, count = 0;
        while (!nodes.isEmpty()) {
            int[] current = nodes.poll();

            if (union(current[0], current[1])) {
                cost += current[2];

                if (++count == V - 1) {
                    break;
                }
            }
        }
        System.out.println(cost);
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

    private static int find(int num) {
        if (parents[num] == num) {
            return num;
        }

        return parents[num] = find(parents[num]);
    }
}
