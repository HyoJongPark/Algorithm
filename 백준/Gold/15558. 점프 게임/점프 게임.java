import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {

    static int N;
    static char[][] bridge;
    static int[][] d = {{0, -1}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d[2][1] = Integer.parseInt(st.nextToken());
        bridge = new char[2][N];
        bridge[0] = br.readLine().toCharArray();
        bridge[1] = br.readLine().toCharArray();

        int result = bfs();
        System.out.println(result);
    }

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();//{x, y, time}
        boolean[][] check = new boolean[2][N];

        q.offer(new int[]{0, 0, 0});
        check[0][0] = true;
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int[] direction : d) {
                int nextX = (current[0] + direction[0]) % 2;
                int nextY = current[1] + direction[1];

                if (nextY >= N) return 1;
                if (isValid(nextY, current[2]) && bridge[nextX][nextY] != '0' && !check[nextX][nextY]) {
                    check[nextX][nextY] = true;
                    q.offer(new int[]{nextX, nextY, current[2] + 1});
                }
            }
        }
        return 0;
    }

    private static boolean isValid(int nextY, int time) {
        return time < nextY && nextY < N;
    }
}
