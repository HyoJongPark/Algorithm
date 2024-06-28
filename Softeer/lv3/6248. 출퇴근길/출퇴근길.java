import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static List<Integer>[] nodes, reverseNodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nodes = new List[N + 1];
        reverseNodes = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            nodes[i] = new ArrayList<>();
            reverseNodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            nodes[A].add(B);
            reverseNodes[B].add(A);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        Set<Integer> visitedNodeA = findVisitedNode(start, target);
        Set<Integer> visitedNodeB = findVisitedNode(target, start);

        visitedNodeA.retainAll(visitedNodeB);
        int result = visitedNodeA.size();
        if (visitedNodeA.contains(start)) {
            result--;
        }
        if (visitedNodeB.contains(target)) {
            result--;
        }
        System.out.println(result);
    }

    private static Set<Integer> findVisitedNode(int start, int target) {
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        dfs(start, target, nodes, s1);
        dfs(start, -1, reverseNodes, s2);

        s1.retainAll(s2);
        return s1;
    }

    private static void dfs(int nodeNo, int targetNode, List<Integer>[] nodes, Set<Integer> visited) {
        if (targetNode != -1 && nodeNo == targetNode) {
            return;
        }

        for (int next : nodes[nodeNo]) {
            if (visited.contains(next)) {
                continue;
            }

            visited.add(next);
            dfs(next, targetNode, nodes, visited);
        }
    }
}
