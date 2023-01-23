import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M, K, X;
    static int[] distance;
    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        distance = new int[N + 1];
        tree = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            tree[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }

        dijkstra();
        StringBuilder sb = new StringBuilder();
        boolean status = false;
        for (int i = 1; i <= N; i++) {
            if (distance[i] == K) {
                status = true;
                sb.append(i).append("\n");
            }
        }

        if (status) {
            System.out.print(sb);
        } else {
            System.out.print(-1);
        }
    }

    private static void dijkstra() {
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(distance, Integer.MAX_VALUE);
        q.offer(X);
        distance[X] = 0;

        while (!q.isEmpty()) {
            Integer current = q.poll();
            for (int next : tree[current]) {
                if (distance[next] > distance[current] + 1) {
                    distance[next] = distance[current] + 1;
                    q.offer(next);
                }
            }
        }
    }
}