import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Nav {
    int currentPoint;
    int level;

    public Nav(int currentPoint, int level) {
        this.currentPoint = currentPoint;
        this.level = level;
    }
}
class Main {
    static int startPoint;
    static int target;
    static boolean[] check = new boolean[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");

        startPoint = Integer.parseInt(s[0]);
        target = Integer.parseInt(s[1]);

        BFS();
    }

    private static void BFS() {
        Queue<Nav> q = new LinkedList<>();
        q.offer(new Nav(startPoint, 0));
        check[startPoint] = true;

        while (!q.isEmpty()) {
            Nav nav = q.poll();
            if (nav.currentPoint == target) {
                System.out.println(nav.level);
                break;
            }

            int[] dx = {nav.currentPoint, -1, 1};
            for (int i = 0; i < dx.length; i++) {
                int nextPoint = nav.currentPoint + dx[i];
                if (nextPoint > check.length - 1) continue;

                if (nextPoint >= 0 && !check[nextPoint] && i == 0) {
                    q.offer(new Nav(nextPoint, nav.level));
                    check[nextPoint] = true;
                } else if (nextPoint >= 0 && !check[nextPoint]) {
                    q.offer(new Nav(nextPoint, nav.level + 1));
                    check[nextPoint] = true;
                }
            }
        }
    }
}