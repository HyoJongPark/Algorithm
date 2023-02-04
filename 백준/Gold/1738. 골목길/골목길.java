import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int[] distance;
    static boolean[] check;
    static List<Node>[] tree;
    static List<Integer>[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        distance = new int[N + 1];
        tree = new ArrayList[N + 1];
        visited = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
            visited[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), distance = Integer.parseInt(st.nextToken());
            tree[a].add(new Node(b, distance));
        }

        if (!bellmanFord()) {
            check = new boolean[N + 1];
            check[N] = true;
            DFS(N, 1);
            System.out.println(sb);
        } else {
            System.out.println(-1);
        }
    }

    //벨만포드로 최대비용 찾기
    private static boolean bellmanFord() {
        Arrays.fill(distance, Integer.MIN_VALUE);
        distance[1] = 0;
        for (int t = 0; t < N; t++) {
            for (int current = 1; current <= N; current++) {
                for (Node next : tree[current]) {
                    if (distance[current] != Integer.MIN_VALUE && distance[next.nodeNo] < distance[current] + next.distance) {
                        visited[next.nodeNo].clear();
                        visited[next.nodeNo].add(current);
                        distance[next.nodeNo] = distance[current] + next.distance;
                    } else if (distance[current] != Integer.MIN_VALUE && distance[next.nodeNo] == distance[current] + next.distance) {
                        visited[next.nodeNo].add(current);
                    }
                }
            }
        }

        return distance[N] == Integer.MIN_VALUE || hasCycle();
    }

    //사이클이 있는지 탐색
    private static boolean hasCycle() {
        for (int current = 1; current <= N; current++) {
            for (Node next : tree[current]) {
                if (distance[current] != Integer.MIN_VALUE && distance[next.nodeNo] < distance[current] + next.distance) {
                    if (next.nodeNo == N || hasCycle(next.nodeNo)) return true;
                }
            }
        }
        return false;
    }

    //사이클이 있다면, 해당 사이클이 N 도시에 도착할 수 있는지 확인
    private static boolean hasCycle(int start) {
        Queue<Integer> q = new LinkedList<>();
        check = new boolean[N + 1];
        check[start] = true;
        q.add(start);
        while (!q.isEmpty()) {
            int current = q.poll();

            for (Node next : tree[current]) {
                if (next.nodeNo == N) return true;
                if (!check[next.nodeNo]) {
                    check[current] = true;
                    q.add(next.nodeNo);
                }
            }
        }

        return check[N];
    }

    private static boolean DFS(int current, int target) {
        if (current == target) {
            sb.append(String.format("%d ", current));
            return true;
        }

        for (int next : visited[current]) {
            if (!check[next]) {
                check[next] = true;
                if (DFS(next, target)) {
                    sb.append(String.format("%d ", current));
                    return true;
                }
                check[next] = false;
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