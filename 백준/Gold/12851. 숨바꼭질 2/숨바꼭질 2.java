import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Position {
    int x;
    int time;

    public Position(int x, int time) {
        this.x = x;
        this.time = time;
    }
}

class Main {

    static int start;
    static int target;
    static int[] dx = {start, -1, 1};
    static int[] check = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        start = tmp[0];
        target = tmp[1];
        if (start == target) {

        }
        int[] bfs = BFS();
        System.out.println(bfs[0]);
        System.out.println(bfs[1]);
    }

    private static int[] BFS() {
        Queue<Position> q = new LinkedList<>();
        q.offer(new Position(start, 0));
        check[start] = 1;

        int time = 0, count = 0;
        boolean status = false;
        while (!q.isEmpty()) {
            Position position = q.poll();
            if (!status) time = position.time;
            if (position.x == target && time == position.time) {
                count++;
                status = true;
            }

            dx[0] = position.x;
            for (int i = 0; i < 3; i++) {
                int next_x = position.x + dx[i];

                if (isValid(next_x, position.time + 1) && time == position.time) {
                    q.offer(new Position(next_x, position.time + 1));
                    check[next_x] = position.time + 1;
                }
            }
        }

        return new int[]{time, count};
    }

    private static boolean isValid(int next_x, int time) {
        return 0 <= next_x && next_x <= 100000 &&
                (check[next_x] == 0 || check[next_x] >= time);
    }
}