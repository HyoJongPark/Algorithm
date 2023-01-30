import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    int[] check, dx;

    public int solution(int x, int y, int n) {
        check = new int[y + 1];
        dx = new int[]{n, 2, 3};
        if (x == y) return 0;
        return BFS(x, y);
    }

    private int BFS(int start, int target) {
        Queue<Integer> q = new LinkedList<>();
        Arrays.fill(check, Integer.MAX_VALUE);
        q.offer(start);
        check[start] = 1;
        while (!q.isEmpty()) {
            Integer current = q.poll();

            for (int i = 0; i < 3; i++) {
                int next = current * dx[i];
                if (i == 0) next = current + dx[i];
                if (next == target) return check[current];
                if (isValid(current, next)) {
                    check[next] = check[current] + 1;
                    q.offer(next);
                }
            }
        }
        return -1;
    }

    private boolean isValid(int current, int next) {
        return 0 < next && next < check.length && check[next] > check[current] + 1;
    }
}