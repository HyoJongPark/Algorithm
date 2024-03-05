import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int T, N, K;
    static int[] buildTime, inDegree;
    static List<Integer>[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            buildTime = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            sequence = new List[N + 1];
            inDegree = new int[N + 1];

            for (int i = 1; i <= N; i++) {
                sequence[i] = new ArrayList<>();
            }
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                sequence[a].add(b);
                inDegree[b]++;
            }

            int result = topologicalSort(Integer.parseInt(br.readLine()));
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static int topologicalSort(int target) {
        Queue<Integer> q = new LinkedList<>();
        int[] result = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            result[i] = buildTime[i - 1];

            if (inDegree[i] == 0) q.offer(i);
        }
        while (!q.isEmpty()) {
            Integer current = q.poll();

            for (int next : sequence[current]) {
                result[next] = Math.max(result[next], result[current] + buildTime[next - 1]);
                
                if (--inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }
        return result[target];
    }
}
