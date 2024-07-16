import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, K, Q, M;
    static int[] psum;
    static boolean[] check, sleep;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        psum = new int[N + 3];
        check = new boolean[N + 3];
        sleep = new boolean[N + 3];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            sleep[Integer.parseInt(st.nextToken())] = true;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            int crr = Integer.parseInt(st.nextToken());
            if (sleep[crr]) continue;

            for (int next = crr; next < check.length; next += crr) {
                if (sleep[next]) continue;

                check[next] = true;
            }
        }

        for (int i = 3; i < check.length; i++) {
            psum[i] = psum[i - 1] + (!check[i] ? 1 : 0);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            sb.append(psum[e] - psum[s - 1]).append("\n");
        }
        System.out.println(sb);
    }
}
