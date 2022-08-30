import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int T, N, cnt = 0;
    static int[] team;
    static boolean[] isDone, check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            team = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            isDone = new boolean[N];
            check = new boolean[N];

            for (int j = 0; j < N; j++) DFS(j);
            System.out.println(N - cnt);
            cnt = 0;
        }
    }

    private static void DFS(int current) {
        check[current] = true;
        int next = team[current] - 1;

        if (!check[next]) DFS(next);
        else if (check[next] && !isDone[next]) {
            cnt++;
            while (current != next) {
                next = team[next] - 1;
                cnt++;
            }
        }

        isDone[current] = true;
    }
}