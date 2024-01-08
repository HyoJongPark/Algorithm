import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static List<Integer>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodes = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nodes[a].add(b);
            nodes[b].add(a);
        }

        int[] result = BFS();
        System.out.printf("%d %d %d", result[0], result[1], result[2]);
    }

    private static int[] BFS() {
        Queue<int[]> q = new LinkedList<>();
        HashMap<Integer, Integer> countResult = new HashMap<>();
        boolean[] check = new boolean[N + 1];

        q.offer(new int[]{1, 0});
        check[1] = true;
        int maxCount = Integer.MIN_VALUE, maxNode = -1;
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (Integer next : nodes[current[0]]) {
                if (check[next]) continue;
                if (maxCount < current[1] + 1) {
                    maxCount = current[1] + 1;
                    maxNode = next;
                } else if (maxCount == current[1] + 1 && maxNode > next) {
                    maxNode = next;
                }

                check[next] = true;
                q.offer(new int[]{next, current[1] + 1});
                countResult.put(current[1] + 1, countResult.getOrDefault(current[1] + 1, 0) + 1);
            }
        }

        return new int[]{maxNode, maxCount, countResult.get(maxCount)};
    }
}
