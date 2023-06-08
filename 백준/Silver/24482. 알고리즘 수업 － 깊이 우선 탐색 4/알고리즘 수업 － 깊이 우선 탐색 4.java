import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M, R, count;
    static int[] check;
    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        check = new int[N + 1];
        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            tree[u].add(v);
            tree[v].add(u);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(tree[i]);
        }

        Arrays.fill(check, -1);
        DFS(0, R);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < check.length; i++) {
            sb.append(check[i]).append("\n");
        }
        System.out.print(sb);
    }

    private static void DFS(int level, int current) {
        check[current] = level;
        for (int i = tree[current].size() - 1; i >= 0; i--) {
            int next = tree[current].get(i);

            if (check[next] == -1) {
                DFS(level + 1, next);
            }
        }
    }
}
