import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static boolean[] check;
    static List<Node> nodes;
    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            check = new boolean[N + 2];
            nodes = new ArrayList<>();
            tree = new ArrayList[N + 2];

            for (int i = 0; i < N + 2; i++) {
                tree[i] = new ArrayList<>();
            }
            for (int i = 0; i < N + 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                nodes.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }

            for (int i = 0; i < nodes.size(); i++) {
                for (int j = i + 1; j < nodes.size(); j++) {
                    if (calculateDistance(nodes.get(i), nodes.get(j)) <= 1000) {
                        tree[i].add(j);
                        tree[j].add(i);
                    }
                }
            }

            if (DFS()) {
                sb.append("happy\n");
            } else {
                sb.append("sad\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean DFS() {
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        check[0] = true;

        while (!q.isEmpty()) {
            Integer current = q.poll();

            for (int next : tree[current]) {
                if (next == N + 1) return true;
                if (!check[next]) {
                    check[next] = true;
                    q.offer(next);
                }
            }
        }

        return false;
    }

    private static int calculateDistance(Node start, Node end) {
        return Math.abs(start.x - end.x) + Math.abs(start.y - end.y);
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}