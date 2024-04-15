import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static int N, count = 0;
    static boolean[] check;
    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        check = new boolean[N + 1];
        tree = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        check[1] = true;
        dfs(0, 1);

        System.out.println(count % 2 != 0 ? "Yes" : "No");
    }

    private static void dfs(int depth, int nodeNo) {
        if (nodeNo != 1 && tree[nodeNo].size() == 1) {
            count += depth;
            return;
        }

        for (int next : tree[nodeNo]) {
            if (check[next]) continue;

            check[next] = true;
            dfs(depth + 1, next);
        }
    }
}
