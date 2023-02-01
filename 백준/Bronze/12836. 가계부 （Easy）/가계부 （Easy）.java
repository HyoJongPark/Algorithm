import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, Q, PIV = 1;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        for (; PIV < N; PIV <<= 1);
        tree = new long[2 * PIV];

        StringBuilder sb = new StringBuilder();
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("1")) {
                update(Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()));
            } else {
                long result = query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                sb.append(result).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void update(int index, long value) {
        index += PIV - 1;
        tree[index] += value;
        while ((index /= 2) != 0) {
            tree[index] = tree[2 * index] + tree[2 * index + 1];
        }
    }

    private static long query(int start, int end) {
        long result = 0;
        start += PIV - 1;
        end += PIV - 1;
        while (start <= end) {
            if (start % 2 == 1) {
                result += tree[start++];
            }
            if (end % 2 == 0) {
                result += tree[end--];
            }

            start /= 2;
            end /= 2;
        }

        return result;
    }
}