import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int INF = -987654321;

    static int N, M, start, end;
    static boolean[] check;
    static int[] cost;
    static long[] distance;
    static List<Node>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            tree[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), -Integer.parseInt(st.nextToken())));
        }
        cost = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (bellmanFord()) {
            System.out.println("Gee");
        } else {
            System.out.println(distance[end]);
        }
    }

    private static boolean bellmanFord() {
        distance = new long[N];
        Arrays.fill(distance, INF);
        distance[start] = cost[start];

        boolean updated;
        for (int i = 0; i < N - 1; i++) {
            updated = false;
            for (int j = 0; j < N; j++) {
                for (Node next : tree[j]) {
                    if (distance[j] != INF && distance[next.nodeNo] < next.distance + distance[j] + cost[next.nodeNo]) {
                        distance[next.nodeNo] = next.distance + distance[j] + cost[next.nodeNo];
                        updated = true;
                    }
                }
            }
            if (!updated) break;
        }

        if (distance[end] == INF) {
            System.out.println("gg");
            System.exit(0);
        }

        for (int j = 0; j < N; j++) {
            for (Node next : tree[j]) {
                if (distance[j] != INF && distance[next.nodeNo] < next.distance + distance[j] + cost[next.nodeNo]) {
                    distance[next.nodeNo] = next.distance + distance[j] + cost[next.nodeNo];
                    check = new boolean[N];
                    if (next.nodeNo == end || DFS(next.nodeNo)) return true;
                }
            }
        }
        return false;
    }

    private static boolean DFS(int nodeNo) {
        if (nodeNo == end) return true;

        check[nodeNo] = true;
        boolean canGo = false;
        for (Node node : tree[nodeNo]) {
            if (!check[node.nodeNo]) {
                check[node.nodeNo] = true;
                canGo = DFS(node.nodeNo);
            }

            if (canGo) return true;
        }
        return false;
    }

    static class Node {
        int nodeNo;
        int distance;

        public Node(int nodeNo, int distance) {
            this.nodeNo = nodeNo;
            this.distance = distance;
        }
    }
}

