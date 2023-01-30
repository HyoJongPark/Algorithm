import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, K, PIV;
    static int[] tree;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        while ((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            for (PIV = 1; PIV < N; PIV <<= 1);
            tree = new int[2 * PIV];
            Arrays.fill(tree, 1);
            st = new StringTokenizer(br.readLine());
            for (int i = PIV; i < PIV + N; i++) {
                int value = Integer.parseInt(st.nextToken());
                if (value == 0) tree[i] = 0;
                else tree[i] = value / Math.abs(value);
            }

            initializeTree();
            while (K-- > 0) {
                st = new StringTokenizer(br.readLine());
                if (st.nextToken().equals("C"))
                    update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                else {
                    sb.append(query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void initializeTree() {
        int start = PIV / 2, end = (PIV + N) / 2;
        while (start != 0) {
            for (int i = start; i < end; i++) {
                tree[i] = tree[2 * i] * tree[2 * i + 1];
            }

            start /= 2;
            end /= 2;
        }
    }

    private static void update(int index, int value) {
        index += PIV - 1;
        if (value == 0) tree[index] = 0;
        else tree[index] = value / Math.abs(value);
        while ((index /= 2) != 0) {
            tree[index] = tree[2 * index] * tree[2 * index + 1];
        }
    }

    private static String query(int start, int end) {
        start += PIV - 1;
        end += PIV - 1;
        int result = 1;
        do {
            if (start % 2 == 1) {
                result *= tree[start++];
            }

            if (end % 2 == 0) {
                result *= tree[end--];
            }

            if (result == 0) {
                return "0";
            }
        } while ((start /= 2) <= (end /= 2));

        return result > 0 ? "+" : "-";
    }
}