import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Main {

    static int N, M, result;
    static List<List<Node>> tree = new ArrayList<>();
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        check = new boolean[N + 1];
        for (int i = 0; i <= N; i++) tree.add(new ArrayList<>());
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            tree.get(nodeA).add(new Node(nodeB, distance));
            tree.get(nodeB).add(new Node(nodeA, distance));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            result = Integer.MAX_VALUE;
            st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int targetNode = Integer.parseInt(st.nextToken());

            check[startNode] = true;
            DFS(startNode, targetNode, 0);
            check[startNode] = false;
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }

    private static void DFS(int startNode, int targetNode, int distance) {
        if (startNode == targetNode) {
            result = Math.min(result, distance);
            return;
        }

        for (Node nextNode : tree.get(startNode)) {
            if (!check[nextNode.node]) {
                check[nextNode.node] = true;
                DFS(nextNode.node, targetNode, distance + nextNode.distance);
                check[nextNode.node] = false;
            }
        }
    }

    static class Node {
        int node;
        int distance;

        public Node(int startNode, int distance) {
            this.node = startNode;
            this.distance = distance;
        }
    }
}