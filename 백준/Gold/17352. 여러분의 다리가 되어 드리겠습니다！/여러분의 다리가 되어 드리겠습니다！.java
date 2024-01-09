import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < N - 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }

        int target = find(1);
        for (int i = 2; i <= N; i++) {
            int current = find(i);

            if (target != current) {
                System.out.printf("%d %d", 1, i);
                System.exit(0);
            }
        }
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a != b) {
            parent[a] = b;
        }
    }

    private static int find(int nodeNo) {
        if (parent[nodeNo] == nodeNo) {
            return nodeNo;
        }
        parent[nodeNo] = find(parent[nodeNo]);
        return parent[nodeNo];
    }
}
