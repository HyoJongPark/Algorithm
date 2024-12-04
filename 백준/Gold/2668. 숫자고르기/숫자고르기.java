import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
    static int N;
    static int[] board;
    static boolean[] check;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N + 1];
        check = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            board[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 1; i <= N; i++) {
            check[i] = true;
            dfs(i, i);
            check[i] = false;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append("\n");
        for (Integer i : result) {
            sb.append(i).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int current, int target) {
        int next = board[current];
        if (next == target) {
            result.add(target);
            return;
        }

        if (!check[next]) {
            check[next] = true;
            dfs(next, target);
            check[next] = false;
        }
    }
}
