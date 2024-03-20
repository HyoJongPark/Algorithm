import java.util.LinkedList;
import java.util.Queue;

class Solution {

    private static final char[] DIRECTION_NAME = {'d', 'l', 'r', 'u'};
    private static final int[][] D = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};

    private boolean[][][] check;

    public static void main(String[] args) {
        new Solution().solution(3, 4, 2, 3, 3, 1, 5);
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        check = new boolean[2_501][n + 1][m + 1];

        return dfs(0, k, new int[]{x, y}, new int[]{r, c}, "");
    }

    private String dfs(int level, int targetCount, int[] current, int[] target, String route) {
        if (level == targetCount) {
            if (current[0] == target[0] && current[1] == target[1]) {
                return route;
            }
            return "impossible";
        }

        for (int i = 0; i < D.length; i++) {
            int nextX = current[0] + D[i][0];
            int nextY = current[1] + D[i][1];

            if (isValid(nextX, nextY) && !check[level + 1][nextX][nextY]) {
                check[level + 1][nextX][nextY] = true;
                String result = dfs(level + 1, targetCount, new int[]{nextX, nextY}, target, route + DIRECTION_NAME[i]);

                if (!result.equals("impossible")) {
                    return result;
                }
            }
        }
        return "impossible";
    }

    private boolean isValid(int nextX, int nextY) {
        return 0 < nextX && nextX < check[0].length && 0 < nextY && nextY < check[0][0].length;
    }
}
