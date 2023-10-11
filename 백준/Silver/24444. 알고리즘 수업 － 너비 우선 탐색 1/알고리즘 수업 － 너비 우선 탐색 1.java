import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, M, R;
    static List<Integer>[] tree;
    static int[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        check = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());


            tree[a].add(b);
            tree[b].add(a);
        }
        for (int i = 1; i <= N; i++) {
            tree[i].sort(Integer::compare);
        }

        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(check[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        int count = 1;

        q.offer(R);
        check[R] = count;
        while (!q.isEmpty()) {
            Integer current = q.poll();

            for (int next : tree[current]) {
                if (check[next] == 0) {
                    check[next] = ++count;
                    q.offer(next);
                }
            }
        }
    }
}
