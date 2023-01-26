import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M, R, cnt = 1;
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
            int A = Integer.parseInt(st.nextToken()), B = Integer.parseInt(st.nextToken());
            tree[A].add(B);
            tree[B].add(A);
        }
        for (int i = 1; i <= N; i++) {
            tree[i].sort(Integer::compare);
        }
        check[R] = cnt++;
        DFS(R);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(check[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void DFS(int node) {
        for (int next : tree[node]) {
            if (check[next] == 0) {
                check[next] = cnt++;
                DFS(next);
            }
        }
    }
}