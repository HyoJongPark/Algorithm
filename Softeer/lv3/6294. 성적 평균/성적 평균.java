import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, K, PIV = 1;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        while (PIV <= N) {
            PIV <<= 1;
        }
        tree = new int[2 * PIV];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[PIV + i] = Integer.parseInt(st.nextToken());
        }
        makeTree();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;

            int sum = query(start, end);
            float result = (float) sum / (end - start + 1);
            sb.append(String.format("%.2f", result)).append("\n");
        }
        System.out.println(sb);
    }

    private static int query(int start, int end) {
        int sum = 0;

        start += PIV;
        end += PIV;
        while (start <= end) {
            if (start % 2 == 1) {
                sum += tree[start++];
            }
            if (end % 2 == 0) {
                sum += tree[end--];
            }

            start /= 2;
            end /= 2;
        }
        return sum;
    }

    private static void makeTree() {
        int start = PIV / 2, end = PIV;

        while (start != 0) {
            for (int i = start; i < end; i++) {
                tree[i] = tree[i * 2] + tree[i * 2 + 1];
            }

            start /= 2;
            end /= 2;
        }
    }
}
