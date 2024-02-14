import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N, T;
    static List<Integer>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        nodes = new List[T + 1];
        for (int i = 0; i <= T; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            nodes[y].add(x);
        }

        int result = BFS();
        System.out.println(result);
    }

    private static int BFS() {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{0, 0, 0});
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int nextY = current[1] - 2; nextY <= Math.min(current[1] + 2, T); nextY++) {
                if (nextY < 0) continue;
                for (int i = 0; i < nodes[nextY].size(); i++) {
                    int nextX = nodes[nextY].get(i);
                    if (isValid(current, nextX)) {
                        if (nextY == T) return current[2] + 1;

                        q.offer(new int[]{nextX, nextY, current[2] + 1});
                        nodes[nextY].remove(i);
                        i--;
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isValid(int[] current, int nextX) {
        return Math.abs(current[0] - nextX) <= 2;
    }
}
