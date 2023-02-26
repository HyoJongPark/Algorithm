import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, x, y, M;
    static boolean[] check;
    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        check = new boolean[N + 1];
        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        DFS(0, x);
        System.out.println(-1);
    }

    private static void DFS(int level, int currentNode) {
        if (currentNode == y) {
            System.out.println(level);
            System.exit(0);
        }

        for (int nextNode : tree[currentNode]) {
            if (!check[nextNode]) {
                check[nextNode] = true;
                DFS(level + 1, nextNode);
                check[nextNode] = false;
            }
        }
    }
}