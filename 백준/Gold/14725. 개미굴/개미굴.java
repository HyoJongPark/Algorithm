import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        Tree root = new Tree();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            Tree current = root;
            int K = Integer.parseInt(st.nextToken());
            for (int j = 0; j < K; j++) {
                String input = st.nextToken();

                if (!current.next.containsKey(input)) {
                    current.next.put(input, new Tree());
                }
                current = current.next.get(input);
            }
        }

        printTree(root, 0);
        System.out.println(sb);
    }

    private static void printTree(Tree current, int depth) {
        Object[] keys = current.next.keySet().toArray();
        Arrays.sort(keys);

        for (Object key : keys) {
            sb.append("--".repeat(depth)).append(key).append("\n");
            printTree(current.next.get(key), depth + 1);
        }
    }

    static class Tree {
        HashMap<String, Tree> next = new HashMap<>();
    }
}
