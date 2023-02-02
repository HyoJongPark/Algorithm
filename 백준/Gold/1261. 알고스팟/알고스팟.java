import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static char[][] board;
    static int[][] check;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        check = new int[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
            Arrays.fill(check[i], Integer.MAX_VALUE);
        }

        BFS();
        System.out.println(check[N - 1][M - 1]);
    }

    private static void BFS() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));
        check[0][0] = 0;

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];

                if (isValid(nextX, nextY)) {
                    if (board[nextX][nextY] == '1' && check[nextX][nextY] > current.count + 1) {
                        pq.offer(new Node(nextX, nextY, current.count + 1));
                        check[nextX][nextY] = current.count + 1;
                    } else if (board[nextX][nextY] == '0' && check[nextX][nextY] > current.count) {
                        pq.offer(new Node(nextX, nextY, current.count));
                        check[nextX][nextY] = current.count;
                    }
                }
            }
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }

    static class Node implements Comparable<Node> {
        int x, y;
        int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public int compareTo(Node o) {
            return this.count - o.count;
        }
    }
}