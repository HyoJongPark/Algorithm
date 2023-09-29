import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static long result = Long.MAX_VALUE;
    static int[][] route;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        route = new int[N][N];
        check = new boolean[N];

        for (int i = 0; i < N; i++) {
            route[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < N; i++) {
            check[i] = true;
            dfs(1, i, i, 0);
            check[i] = false;
        }
        System.out.println(result);
    }

    private static void dfs(int level, int start, int current, long distance) {
        if (level == N) {
            if (route[current][start] != 0) {
                result = Math.min(result, distance + route[current][start]);
            }
            return;
        }

        for (int next = 0; next < N; next++) {
            if (!check[next] && route[current][next] != 0) {
                check[next] = true;
                dfs(level + 1, start, next, distance + route[current][next]);
                check[next] = false;
            }
        }
    }
}