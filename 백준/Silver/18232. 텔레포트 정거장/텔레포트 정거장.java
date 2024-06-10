import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N, M, S, E;
    static List<Integer>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        nodes = new List[N + 1];
        for (int i = 1; i < N + 1; i++) {
            nodes[i] = new ArrayList<>();

            if (i - 1 > 0) {
                nodes[i].add(i - 1);
            }
            if (i + 1 <= N) {
                nodes[i].add(i + 1);
            }
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            nodes[a].add(b);
            nodes[b].add(a);
        }

        int bfs = bfs(S, E);
        System.out.println(bfs);
    }

    private static int bfs(int start, int target) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];

        q.offer(new int[]{start, 0});
        visited[start] = true;
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int next : nodes[current[0]]) {
                if (next == target) {
                    return current[1] + 1;
                }

                if (!visited[next]) {
                    visited[next] = true;
                    q.offer(new int[]{next, current[1] + 1});
                }
            }
        }
        return -1;
    }
}
