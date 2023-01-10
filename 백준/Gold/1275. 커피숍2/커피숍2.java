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
        while (PIV < N) {
            PIV <<= 1;
        }
        tree = new long[PIV * 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tree[PIV + i] = Integer.parseInt(st.nextToken());
        }
        makeTree();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            sb.append(print(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))).append("\n");
            changeValue(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        System.out.println(sb);
    }

    private static void makeTree() {
        int start = PIV / 2, end = PIV;
        while (start != 0) {
            for (int i = start; i < end; i++) {
                tree[i] = tree[2 * i] + tree[2 * i + 1];
            }
            start /= 2;
            end /= 2;
        }
    }

    private static long print(int x, int y) {
        if (x > y) {
            int tmp = y;
            y = x;
            x = tmp;
        }

        x += PIV - 1;
        y += PIV - 1;
        long sum = 0;
        while (x <= y) {
            if (x % 2 == 1) {
                sum += tree[x++];
            }
            if (y % 2 == 0) {
                sum += tree[y--];
            }
            x /= 2;
            y /= 2;
        }
        return sum;
    }

    private static void changeValue(int position, int nextValue) {
        position += PIV - 1;
        long difference = nextValue - tree[position];
        while (position != 0) {
            tree[position] += difference;
            position /= 2;
        }
    }
}