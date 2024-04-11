import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import javax.swing.JInternalFrame;

class Main {

    static int N, M;
    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        int count = 0;
        for (; M > 0; M--) {
            st = new StringTokenizer(br.readLine());

            if (!union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))) {
                count++;
            }
        }

        for (int i = 1; i <= N; i++) {
            if (parents[i] == i) count++;
        }
        System.out.println(count - 1);
    }

    private static boolean union(int st, int en) {
        st = find(st);
        en = find(en);

        if (st == en) return false;

        if (st > en) {
            parents[st] = en;
        } else {
            parents[en] = st;
        }
        return true;
    }

    private static int find(int num) {
        if (parents[num] == num) {
            return num;
        }
        return parents[num] = find(parents[num]);
    }
}
