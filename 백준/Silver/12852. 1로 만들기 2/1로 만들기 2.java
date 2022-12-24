import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static int minDistance = Integer.MAX_VALUE;
    static String answer = "";
    static int[] dx = {3, 2, 1};
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        check = new boolean[1000001];
        DFS(0, N, String.valueOf(N));
        System.out.println(minDistance);
        System.out.println(answer);
    }

    private static void DFS(int level, int n, String log) {
        if (level >= minDistance) return;
        if (n == 1) {
            minDistance = level;
            answer = log;
            return;
        }
        int[] nextX = {n / 3, n / 2, n - 1};
        for (int i = 0; i < dx.length; i++) {
            if (n % dx[i] == 0 && 1 <= nextX[i] && nextX[i] <= check.length && !check[nextX[i]]) {
                DFS(level + 1, nextX[i], log + " " + nextX[i]);
            }
        }
    }
}