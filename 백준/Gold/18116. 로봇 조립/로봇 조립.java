import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, numberOfRobot = 1_000_000;
    static int[] parents = new int[numberOfRobot + 1];
    static int[] count = new int[numberOfRobot + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= numberOfRobot; i++) {
            parents[i] = i;
            count[i] = 1;
        }

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            if (st.nextToken().equals("I")) {
                union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else {
                int curr = find(Integer.parseInt(st.nextToken()));
                sb.append(count[curr]).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static void union(int st, int en) {
        st = find(st);
        en = find(en);

        if (st != en) {
            if (st > en) {
                count[en] += count[st];
                parents[st] = en;
            } else {
                count[st] += count[en];
                parents[en] = st;
            }
        }
    }

    private static int find(int nodeNo) {
        if (nodeNo == parents[nodeNo]) {
            return nodeNo;
        }
        return parents[nodeNo] = find(parents[nodeNo]);
    }
}
