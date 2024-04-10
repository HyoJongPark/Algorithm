import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int T, N, K;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= T; i++) {
            N = Integer.parseInt(br.readLine());
            K = Integer.parseInt(br.readLine());
            parents = new int[N];

            for (int j = 0; j < N; j++) {
                parents[j] = j;
            }

            StringTokenizer st;
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            sb.append("Scenario ").append(i).append(":\n");
            int M = Integer.parseInt(br.readLine());
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());

                if (find(Integer.parseInt(st.nextToken())) == find(Integer.parseInt(st.nextToken()))) {
                    sb.append("1\n");
                } else {
                    sb.append("0\n");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void union(int st, int en) {
        st = find(st);
        en = find(en);

        if (st > en) {
            parents[st] = en;
        } else if (st < en) {
            parents[en] = st;
        }
    }

    private static int find(int num) {
        if (num == parents[num]) {
            return num;
        }
        return parents[num] = find(parents[num]);
    }
}
