import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
    static int N, M, PIV = 1;
    static Node[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (; PIV < N; PIV <<= 1) ;
        tree = new Node[2 * PIV];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = PIV; i < PIV + N; i++) {
            tree[i] = new Node(i - PIV + 1, Integer.parseInt(st.nextToken()));
        }
        initializeTree();

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            if (st.nextToken().equals("1")) {
                update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else {
                int resultIndex = query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                sb.append(resultIndex).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void initializeTree() {
        int start = PIV / 2, end = (PIV + N) / 2;
        while (start != 0) {
            for (int i = start; i < end; i++) {
                if (tree[2 * i].compareTo(tree[2 * i + 1]) > 0) {
                    tree[i] = tree[2 * i + 1];
                } else {
                    tree[i] = tree[2 * i];
                }
            }
            start /= 2;
            end /= 2;
        }
    }

    private static void update(int index, int value) {
        index += PIV - 1;
        tree[index].updateValue(value);
        while ((index /= 2) != 0) {
            if (tree[2 * index].compareTo(tree[2 * index + 1]) > 0) {
                tree[index] = tree[2 * index + 1];
            } else {
                tree[index] = tree[2 * index];
            }
        }
    }

    private static int query(int start, int end) {
        Node resultNode = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE);
        start += PIV - 1;
        end += PIV - 1;

        while (start <= end) {
            if (start % 2 == 1) {
                if (resultNode.compareTo(tree[start]) > 0) {
                    resultNode = tree[start];
                }
                start++;
            }

            if (end % 2 == 0) {
                if (resultNode.compareTo(tree[end]) > 0) {
                    resultNode = tree[end];
                }
                end--;
            }

            start /= 2;
            end /= 2;
        }

        return resultNode.index;
    }

    static class Node {

        int index;
        int value;

        public Node(int index, int value) {
            this.index = index;
            this.value = value;
        }

        public void updateValue(int value) {
            this.value = value;
        }

        public int compareTo(Node o) {
            if (Objects.isNull(o)) return -1;
            if (this.value == o.value) return this.index - o.index;
            return this.value - o.value;
        }
    }
}