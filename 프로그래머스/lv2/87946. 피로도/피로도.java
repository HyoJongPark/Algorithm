import java.util.Arrays;
import java.util.Comparator;

class Solution {

    static int k, max = Integer.MIN_VALUE;
    static int[][] dungeons;
    static boolean[] check;

    public int solution(int k, int[][] dungeons) {
        Solution.k = k;
        Solution.dungeons = dungeons;
        check = new boolean[dungeons.length];

        DFS(0, k);
        return max;
    }

    private void DFS(int level, int current) {
        if (level == dungeons.length) {
            max = Math.max(level, max);
            return;
        }

        boolean canGo = false;
        for (int i = 0; i < dungeons.length; i++) {
            if (!check[i] && current >= dungeons[i][0]) {
                check[i] = true;
                canGo = true;
                DFS(level + 1, current - dungeons[i][1]);
                check[i] = false;
            }
        }

        if (!canGo) {
            max = Math.max(max, level);
        }
    }
}