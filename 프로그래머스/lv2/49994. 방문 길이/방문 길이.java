import java.util.*;

class Solution {
    
    static boolean[][][] check = new boolean[11][11][4];
    static final Map<Character, Integer> dirCode = new HashMap<>();
    static final int[][] d = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    
    public int solution(String dirs) {
        //initialize
        dirCode.put('U', 0);
        dirCode.put('D', 1);
        dirCode.put('R', 2);
        dirCode.put('L', 3);
        
        int result = 0;
        int[] position = new int[]{5, 5};
        for (Character i : dirs.toCharArray()) {
            int code = dirCode.get(i);
            int[] nextPosition = new int[]{position[0] + d[code][0], position[1] + d[code][1]};
            
            if (!isValid(nextPosition[0], nextPosition[1])) {
                continue;
            }
            if (!check[position[0]][position[1]][code]) {
                check[position[0]][position[1]][code] = true;
                
                if (code % 2 == 0) code++;
                else code--;
                check[nextPosition[0]][nextPosition[1]][code] = true;
                result++;
            }
            position = nextPosition;
        }
        
        return result;
    }
    
    private boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX <= 10 && 0 <= nextY && nextY <= 10;
    }
}