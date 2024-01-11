import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] parents;
    static List<Node> nodes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (st.nextToken().equals("1")) {
                    nodes.add(new Node(i, j));
                }
            }
        }

        int count = 0;
        for (Node node : nodes) {
            if (union(node.start, node.end)) {
                count++;

                if (count == N - 1) {
                    break;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int parentNo = find(Integer.parseInt(st.nextToken()));
        boolean status = true;
        for (int i = 0; i < M - 1; i++) {
            if (parentNo != find(Integer.parseInt(st.nextToken()))) {
                status = false;
                break;
            }
        }

        if (status) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean union(int start, int end) {
        start = find(start);
        end = find(end);

        if (start == end) {
            return false;
        }

        if (start > end) {
            parents[start] = end;
        } else {
            parents[end] = start;
        }
        return true;
    }

    private static int find(int nodeNo) {
        if (nodeNo == parents[nodeNo]) {
            return nodeNo;
        }
        parents[nodeNo] = find(parents[nodeNo]);
        return parents[nodeNo];
    }

    static class Node {
        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
