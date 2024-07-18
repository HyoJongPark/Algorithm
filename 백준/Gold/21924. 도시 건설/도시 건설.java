import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] parents;
    static PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        long totalCost = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            pq.offer(new int[]{a, b, c});
            totalCost += c;
        }

        long result = mst();
        System.out.println(result == -1 ? -1 : totalCost - result);
    }

    private static long mst() {
        long cost = 0;
        int count = 0;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();

            if (union(current[0], current[1])) {
                cost += current[2];

                if (++count == N - 1) {
                    return cost;
                }
            }
        }
        return -1;
    }

    private static boolean union(int st, int en) {
        st = find(st);
        en = find(en);

        if (st == en) {
            return false;
        } else if (st < en) {
            parents[en] = st;
        } else {
            parents[st] = en;
        }

        return true;
    }

    private static int find(int num) {
        if (parents[num] == num) {
            return num;
        }
        return parents[num] = find(parents[num]);
    }
}
