import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static long result;
    static final int[] NUMBER_OF_STATION = {5, 11};
    static final int MOD_NUM = (int) Math.pow(10, 9) + 7;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            br.readLine();
        }
        for (int i = 0; i < 2; i++) {
            DFS(1, 1, 1, NUMBER_OF_STATION[i], i);
        }
        System.out.println(result);
    }

    private static void DFS(int level, int maxIntervalDistance, int intervalDistance, long count, int status) {
        if (intervalDistance > M) {
            return;
        }
        if (level == N) {
            if (maxIntervalDistance == M) {
                result = (result + count) % MOD_NUM;
            }
            return;
        }

        for (int i = 0; i < 2; i++) {
            long nextCount = (count * NUMBER_OF_STATION[i]) % MOD_NUM;

            if (i == status) {
                DFS(level + 1, Math.max(maxIntervalDistance, intervalDistance),
                        1, nextCount, i);
            } else {
                DFS(level + 1, Math.max(maxIntervalDistance, intervalDistance + 1),
                        intervalDistance + 1, nextCount, i);
            }
        }
    }
}