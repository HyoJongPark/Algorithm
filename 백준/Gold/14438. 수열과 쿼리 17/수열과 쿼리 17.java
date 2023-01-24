import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M, PIV = 1;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (; PIV < N; PIV <<= 1) ;
        tree = new int[2 * PIV];
        Arrays.fill(tree, Integer.MAX_VALUE);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = PIV; i < PIV + N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
        }
        initializeTree();

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("1")) {
                update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else {
                int result = query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                sb.append(result).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void initializeTree() {
        int start = PIV / 2, end = (PIV + N) / 2;
        while (start != 0) {
            for (int i = start; i < end; i++) {
                tree[i] = Math.min(tree[2 * i + 1], tree[2 * i]);
            }
            start /= 2;
            end /= 2;
        }
    }

    private static void update(int index, int value) {
        index += PIV - 1;
        tree[index] = value;
        while ((index /= 2) != 0) {
            tree[index] = Math.min(tree[2 * index + 1], tree[2 * index]);
        }
    }

    private static int query(int start, int end) {
        int result = Integer.MAX_VALUE;
        start += PIV - 1;
        end += PIV - 1;

        while (start <= end) {
            if (start % 2 == 1) {
                result = Math.min(result, tree[start++]);
            }
            if (end % 2 == 0) {
                result = Math.min(result, tree[end--]);
            }

            start /= 2;
            end /= 2;
        }

        return result;
    }
}