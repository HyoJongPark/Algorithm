import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, PIV = 1 << 20;
    static int[] tree = new int[2 * PIV];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            if (A == 1) {
                int result = query(B);
                update(result, -1);
                sb.append(result).append("\n");
            } else {
                int C = Integer.parseInt(st.nextToken());
                update(B, C);
            }
        }
        System.out.println(sb);
    }

    private static void update(int position, int numberOfCandy) {
        position += PIV - 1;
        while (position != 0) {
            tree[position] += numberOfCandy;
            position /= 2;
        }
    }

    private static int query(int n) {
        int index = 1;
        while (index < PIV) {
            index *= 2;
            if (tree[index] < n) {
                n -= tree[index];
                index += 1;
            }
        }
        index -= PIV - 1;
        return index ;
    }
}