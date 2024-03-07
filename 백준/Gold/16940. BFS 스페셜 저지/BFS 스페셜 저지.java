import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N;
    static List<Integer>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nodes[a].add(b);
            nodes[b].add(a);
        }

        int[] expectedResult = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int result = BFS(expectedResult);
        System.out.println(result);
    }

    private static int BFS(int[] expectedResult) {
        if (expectedResult[0] != 1) return 0;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        q.offer(1);
        visited[1] = true;
        int index = 1;
        while (!q.isEmpty()) {
            Integer current = q.poll();

            int count = 0;
            for (int next : nodes[current]) {
                if (!visited[next]) {
                    visited[next] = true;
                    count++;
                }
            }

            for (int i = index; i < index + count; i++) {
                if (!visited[expectedResult[i]]) {
                    return 0;
                }
                q.offer(expectedResult[i]);
            }
            index += count;
        }
        return 1;
    }
}
