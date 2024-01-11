import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int N, M, k;
    static int[] cost, parents;
    static List<Node> nodes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            nodes.add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
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
        System.out.println(calculateCost());
    }

    private static int calculateCost() {
        int[] check = new int[N + 1];
        int result = 0;
        for (int i = 1; i <= N; i++) {
            int curr = find(i);

            if (check[curr] == 0 || check[curr] > cost[i - 1]) {
                result += cost[i - 1] - check[curr];
                check[curr] = cost[i - 1];
            }
        }

        if (result > k) {
            System.out.println("Oh no");
            System.exit(0);
        }
        return result;
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
        if (parents[nodeNo] == nodeNo) {
            return nodeNo;
        }
        return parents[nodeNo] = find(parents[nodeNo]);
    }

    static class Node {
        int start, end;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
