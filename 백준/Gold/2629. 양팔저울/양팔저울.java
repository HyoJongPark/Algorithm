import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int N, M;
    static int[] weight, target;
    static boolean[][] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        weight = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(br.readLine());
        target = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result = new boolean[N + 1][40001];

        dp(0, 0);

        StringBuilder sb = new StringBuilder();
        for (int t : target) {
            if (result[N][t]) {
                sb.append("Y ");
            } else {
                sb.append("N ");
            }
        }
        System.out.println(sb);
    }

    private static void dp(int count, int num) {
        if (result[count][num]) return;

        result[count][num] = true;
        if (count == N) return;

        dp(count + 1, num);
        dp(count + 1, num + weight[count]);
        dp(count + 1, Math.abs(num - weight[count]));
    }
}
