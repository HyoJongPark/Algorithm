import java.util.*;

class Solution {
    int[] check;
    List<Integer>[] tree;


    public int solution(int n, int[][] edge) {
        check = new int[n + 1];
        tree = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < edge.length; i++) {
            int[] current = edge[i];
            tree[current[1]].add(current[0]);
            tree[current[0]].add(current[1]);
        }
        int distance = BFS(edge);
        return (int) Arrays.stream(check)
                .filter(d -> d == distance)
                .count();
    }

    private int BFS(int[][] edge) {
        Queue<int[]> q = new LinkedList<>();
        Arrays.fill(check, Integer.MAX_VALUE);
        q.offer(new int[]{1, 1});
        check[1] = 1;

        int maxDistance = 0;
        while (!q.isEmpty()) {
            int[] current = q.poll();
            int nextDistance = current[1] + 1;

            for (int next : tree[current[0]]) {
                if (check[next] > nextDistance) {
                    maxDistance = nextDistance;
                    check[next] = nextDistance;
                    q.offer(new int[]{next, nextDistance});
                }
            }
        }

        return maxDistance;
    }
}