import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int[] cost, inDegree;
    static List<Integer>[] works;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N + 1];
        inDegree = new int[N + 1];
        works = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            works[i] = new ArrayList<>();

            int total = Integer.parseInt(st.nextToken());
            for (int j = 0; j < total; j++) {
                int crr = Integer.parseInt(st.nextToken());
                works[crr].add(i);
                inDegree[i]++;
            }
        }

        int[] resultCost = topologicalSort();
        int answer = resultCost[1];
        for (int i = 2; i <= N; i++) {
            answer = Math.max(resultCost[i], answer);
        }
        System.out.println(answer);
    }

    private static int[] topologicalSort() {
        Queue<Integer> q = new LinkedList<>();

        int[] result = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            result[i] = cost[i];

            if (inDegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            Integer current = q.poll();

            for (int next : works[current]) {
                result[next] = Math.max(result[next], result[current] + cost[next]);

                if (--inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        return result;
    }
}
