import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int N, D;
    static int[] distance;
    static List<int[]>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        distance = new int[D + 1];
        nodes = new List[D + 1];
        for (int i = 1; i <= D; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            if (b > D) continue;
            nodes[b].add(new int[]{a, distance});
        }

        for (int i = 1; i <= D; i++) {
            distance[i] = distance[i - 1] + 1;

            for (int[] next : nodes[i]) {
                if (distance[i] > distance[next[0]] + next[1]) {
                    distance[i] = distance[next[0]] + next[1];
                }
            }
        }
        System.out.println(distance[D]);
    }
}
