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
    static List<List<Integer>> sequence = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i <= N; i++) {
            sequence.add(new ArrayList<>());
        }

        int[] inDegree = new int[N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int numOfSinger = Integer.parseInt(st.nextToken());
            int before = Integer.parseInt(st.nextToken());
            for (int j = 0; j < numOfSinger - 1; j++) {
                int current = Integer.parseInt(st.nextToken());
                sequence.get(before).add(current);
                inDegree[current]++;

                before = current;
            }
        }

        System.out.println(topologicalSort(inDegree));
    }

    private static String topologicalSort(int[] inDegree) {
        Queue<Integer> q = new LinkedList<>();
        ArrayList<Integer> result = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            Integer current = q.poll();

            result.add(current);
            for (int next : sequence.get(current)) {
                if (--inDegree[next] == 0) {
                    q.offer(next);
                }
            }
        }

        if (result.size() != N) return "0";

        StringBuilder sb = new StringBuilder();
        for (Integer c : result) {
            sb.append(c).append("\n");
        }
        return sb.toString();
    }
}
