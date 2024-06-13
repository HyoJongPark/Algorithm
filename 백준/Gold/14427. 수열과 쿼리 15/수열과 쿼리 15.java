import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int N, M, PIV = 1, INF = 1_000_000_001;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        while (PIV <= N) {
            PIV = PIV << 1;
        }
        tree = new int[2 * PIV];
        Arrays.fill(tree, INF);

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[PIV + i] = Integer.parseInt(st.nextToken());
        }
        makeTree();

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            if (st.nextToken().equals("1")) {
                update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else {
                sb.append(find()).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static int find() {
        int index = 1;
        while (index < PIV) {
            if (tree[index * 2] == tree[index]) {
                index = index * 2;
            } else {
                index = index * 2 + 1;
            }
        }
        return index - PIV + 1;
    }

    private static void update(int index, int value) {
        index += PIV - 1;
        tree[index] = value;

        while (index != 0) {
            if (index % 2 == 0) {
                tree[index / 2] = Math.min(tree[index], tree[index + 1]);
            } else {
                tree[index / 2] = Math.min(tree[index], tree[index - 1]);
            }

            index /= 2;
        }
    }

    private static void makeTree() {
        int start = PIV / 2, end = PIV;
        while (start != 0) {
            for (int i = start; i < end; i++) {
                tree[i] = Math.min(tree[2 * i], tree[2 * i + 1]);
            }

            start /= 2;
            end /= 2;
        }
    }
}
