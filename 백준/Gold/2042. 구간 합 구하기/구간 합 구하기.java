import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M, K, PIV = 1 << 20;
    static long[] tree = new long[2 * PIV];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            tree[PIV + i] = Long.parseLong(br.readLine());
        }
        makeTree();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());

            if (st.nextToken().equals("1")) {
                change(Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()));
            } else {
                long sum = print(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

                sb.append(sum).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static long print(int start, int end) {
        long sum = 0;
        start += PIV - 1;
        end += PIV - 1;

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

    private static void change(int index, long value) {
        index += PIV - 1;
        long differ = value - tree[index];

        while (index != 0) {
            tree[index] += differ;
            index /= 2;
        }
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
