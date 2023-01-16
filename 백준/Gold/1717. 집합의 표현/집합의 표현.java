import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            if (st.nextToken().equals("0")) {
                union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else {
                int resultA = find(Integer.parseInt(st.nextToken()));
                int resultB = find(Integer.parseInt(st.nextToken()));
                if (resultA == resultB) {
                    sb.append("YES").append("\n");
                } else {
                    sb.append("NO").append("\n");
                }
            }
        }
        System.out.println(sb);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            tree[a] = b;
        }
    }

    private static int find(int a) {
        if (tree[a] != a) {
            tree[a] = find(tree[a]);
        }
        return tree[a];
    }
}