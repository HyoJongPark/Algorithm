import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    static int N, M;
    static int[] edgeCount;
    static List<List<Integer>> tree = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edgeCount = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            tree.get(a).add(b);
            edgeCount[b]++;
        }

        topologySort();
    }

    private static void topologySort() {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            if (edgeCount[i] == 0) {
                q.offer(i);
            }
        }

        while (!q.isEmpty()) {
            int student = q.poll();
            sb.append(student).append(" ");

            for (Integer nextStudent : tree.get(student)) {
                edgeCount[nextStudent]--;
                if (edgeCount[nextStudent] == 0) {
                    q.offer(nextStudent);
                }
            }
        }
        System.out.println(sb);
    }
}