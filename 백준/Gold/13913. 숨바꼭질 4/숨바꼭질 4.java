import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, target, maxValue;
    static int[] check;
    static List<Integer>[] visited;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {1, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        if (N == target) {
            System.out.printf("0\n%d", N);
            System.exit(0);
        } else if (N > target) {
            dx = new int[]{-1, -1};
        }
        maxValue = 2 * Math.max(N, target);
        check = new int[maxValue + 1];
        visited = new ArrayList[maxValue + 1];
        for (int i = 0; i <= maxValue; i++) {
            visited[i] = new ArrayList<>();
        }
        sb.append(BFS()).append("\n");
        check = new int[maxValue + 1];
        DFS(target);
        System.out.println(sb);
    }

    private static int BFS() {
        Queue<Node> q = new LinkedList<>();
        Arrays.fill(check, Integer.MAX_VALUE);
        q.offer(new Node(N, 0));
        check[N] = 0;

        while (!q.isEmpty()) {
            Node current = q.poll();

            int next = current.nodeNum * 2;
            for (int i = 0; i < dx.length; i++) {
                if (next == target) {
                    visited[target].add(current.nodeNum);
                    return current.time + 1;
                }
                if (isValid(next, current.time)) {
                    check[next] = current.time + 1;
                    visited[next].add(current.nodeNum);
                    q.offer(new Node(next, current.time + 1));
                }
                next = current.nodeNum + dx[i];
            }
        }

        return -1;
    }

    private static boolean DFS(int current) {
        if (current == N) {
            sb.append(current).append(" ");
            return true;
        }

        for (int next : visited[current]) {
            if (check[next] == 0) {
                check[next] = 1;
                if (DFS(next)) {
                    sb.append(current).append(" ");
                    return true;
                }
                check[next] = 0;
            }
        }

        return false;
    }

    private static boolean isValid(int next, int time) {
        return 0 <= next && next <= maxValue && check[next] > time;
    }

    static class Node {
        int nodeNum;
        int time;

        public Node(int nodeNum, int time) {
            this.nodeNum = nodeNum;
            this.time = time;
        }
    }
}