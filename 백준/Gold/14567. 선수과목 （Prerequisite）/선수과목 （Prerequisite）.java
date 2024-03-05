import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] inDegree;
    static List<Integer>[] sequence;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inDegree = new int[N + 1];
        sequence = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            sequence[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int before = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());

            inDegree[next]++;
            sequence[before].add(next);
        }

        int[] result = topologicalSort();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(result[i]).append(" ");
        }
        System.out.print(sb);
    }

    private static int[] topologicalSort() {
        Queue<int[]> q = new LinkedList<>();
        int[] result = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) q.offer(new int[]{i, 1});
        }

        while (!q.isEmpty()) {
            int[] current = q.poll();

            result[current[0]] = current[1];
            for (int next : sequence[current[0]]) {
                if (--inDegree[next] == 0) q.offer(new int[]{next, current[1] + 1});
            }
        }
        return result;
    }
}
