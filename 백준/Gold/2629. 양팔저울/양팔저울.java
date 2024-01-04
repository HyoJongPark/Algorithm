import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] weight;
    static boolean[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weight = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result =  new boolean[N + 1][40_001];
        M = Integer.parseInt(br.readLine());

        dp(0, 0);

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            if (result[N][Integer.parseInt(st.nextToken())]) {
                sb.append("Y ");
            } else {
                sb.append("N ");
            }
        }
        System.out.println(sb);
    }

    private static void dp(int level, int current) {
        if (result[level][current]) {
            return;
        }

        result[level][current] = true;
        if (level < N) {
            dp(level + 1, current);
            dp(level + 1, current + weight[level]);
            dp(level + 1, Math.abs(current - weight[level]));
        }
    }
}
