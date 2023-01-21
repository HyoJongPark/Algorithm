import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M, PIV = 1;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (; PIV < N; PIV <<= 1) ;
        tree = new long[2 * PIV];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("0")) {
                long result = query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                sb.append(result).append("\n");
            } else {
                update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
        }
        System.out.print(sb);
    }

    private static long query(int start, int end) {
        if (start > end) {
            int tmp = start;
            start = end;
            end = tmp;
        }
        start += PIV - 1;
        end += PIV - 1;
        long sum = 0;
        while (start <= end){
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

    private static void update(int index, int value) {
        index += PIV - 1;
        tree[index] = value;
        while ((index /= 2) != 0) {
            tree[index] = tree[2 * index] + tree[2 * index + 1];
        }
    }
}