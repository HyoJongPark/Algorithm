import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static int N;
    static int[] bridge;
    static int[] dx = {-1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bridge = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int result = BFS(Integer.parseInt(br.readLine()));
        System.out.println(result);
    }

    private static int BFS(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] check = new boolean[N + 1];
        int count = 1;

        q.offer(start);
        check[start] = true;
        while (!q.isEmpty()) {
            Integer current = q.poll();

            for (int i = 0; i < 2; i++) {
                int next = current + dx[i] * bridge[current - 1];

                if (isValid(next) && !check[next]) {
                    q.offer(next);
                    check[next] = true;
                    count++;
                }
            }
        }

        return count;
    }

    private static boolean isValid(int next) {
        return 1 <= next && next <= N;
    }
}