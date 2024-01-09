import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int N;
    static boolean[] check;
    static List<Integer>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        check = new boolean[N + 1];
        nodes = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            nodes[a].add(b);
            nodes[b].add(a);
        }

        check[0] = true;
        check[1] = true;
        DFS(1);

        for (int i = 2; i <= N; i++) {
            if (!check[i]) {
                System.out.printf("%d %d", 1, i);
                System.exit(0);
            }
        }
    }

    private static void DFS(int curr) {
        for (Integer next : nodes[curr]) {
            if (!check[next]) {
                check[next] = true;
                DFS(next);
            }
        }
    }
}
