import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int T, N, M, W;
    static List<Node>[] tree;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            tree = new ArrayList[N + 1];
            for (int i = 0; i <= N; i++) {
                tree[i] = new ArrayList<>();
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), distance = Integer.parseInt(st.nextToken());
                tree[a].add(new Node(b, distance));
                tree[b].add(new Node(a, distance));
            }
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                tree[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), -Integer.parseInt(st.nextToken())));
            }

            if (bellmanFord()) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean bellmanFord() {
        long[] distances = new long[N + 1];
        Arrays.fill(distances, 987654321);
        distances[1] = 0;

        boolean isUpdated;
        for (int i = 0; i < N; i++) {
            isUpdated = false;
            for (int current = 1; current <= N; current++) {
                for (Node next : tree[current]) {
                    if (distances[current] != Long.MAX_VALUE && distances[next.nodeNo] > next.distance + distances[current]) {
                        distances[next.nodeNo] = next.distance + distances[current];
                        isUpdated = true;
                    }
                }
            }
            if (!isUpdated) return false;
        }

        for (int current = 1; current <= N; current++) {
            for (Node next : tree[current]) {
                if (distances[next.nodeNo] > next.distance + distances[current]) {
                    return true;
                }
            }
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
