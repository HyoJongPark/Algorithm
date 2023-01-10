import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M, K, PIV = 1;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        while (PIV <= N) {
            PIV = PIV << 1;
        }
        tree = new long[2 * PIV];
        tree[PIV] = Long.parseLong(br.readLine());
        for (int i = 1; i < N; i++) {
            tree[PIV + i] = Long.parseLong(br.readLine());
        }
        makeTree(PIV);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("1")) {
                change(Integer.parseInt(st.nextToken()), Long.parseLong(st.nextToken()));
            } else {
                sb.append(print(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static void makeTree(int PIV) {
        int start = PIV / 2, end = PIV;
        while (start != 0) {
            for (int i = start; i < end; i++) {
                tree[i] = tree[i * 2] + tree[i * 2 + 1];
            }
            end /= 2;
            start /= 2;
        }
    }

    private static void change(int position, long nextValue) {
        position += PIV - 1;
        long difference = nextValue - tree[position];
        while (position > 0) {
            tree[position] += difference;
            position /= 2;
        }
    }

    private static long print(int start, int end) {
        start += PIV - 1;
        end += PIV - 1;
        long sum = 0;
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
}