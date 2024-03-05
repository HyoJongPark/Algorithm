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
    static List<List<Integer>> sequence = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        inDegree = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            sequence.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int before = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());

            inDegree[next]++;
            sequence.get(before).add(next);
        }

        List<Integer> result = topologicalSort();
        StringBuilder sb = new StringBuilder();
        for (int i : result) {
            sb.append(i).append(" ");
        }
        System.out.print(sb);
    }

    private static List<Integer> topologicalSort() {
        Queue<Integer> q = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) q.offer(i);
        }

        while (!q.isEmpty()) {
            Integer current = q.poll();

            result.add(current);
            for (int next : sequence.get(current)) {
                if (--inDegree[next] == 0) q.offer(next);
            }
        }
        return result;
    }
}
