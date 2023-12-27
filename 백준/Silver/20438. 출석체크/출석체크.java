import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Main {

    static int N, K, Q, M;
    static boolean[] check;
    static int[] prefixSum;
    static Set<Integer> sleep = new HashSet<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        check = new boolean[N + 3];
        prefixSum = new int[N + 3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            sleep.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int current = Integer.parseInt(st.nextToken());
            if (!sleep.contains(current) && !check[current]) {
                checkStudent(current);
            }
        }

        for (int i = 3; i < N + 3; i++) {
            prefixSum[i] = prefixSum[i - 1] + (check[i] ? 0 : 1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            sb.append(prefixSum[E] - prefixSum[S - 1]).append("\n");
        }
        System.out.println(sb);
    }

    private static void checkStudent(int current) {
        int next, count = 1;
        while ((next = current * count++) < N + 3) {
            if (!sleep.contains(next)) {
                check[next] = true;
            }
        }
    }
}
