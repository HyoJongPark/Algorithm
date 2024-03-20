import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        
        int cctvPos = routes[0][1], count = 1;
        for (int i = 1; i < routes.length; i++) {
            int[] current = routes[i];
            
            if (current[0] > cctvPos) {
                cctvPos = current[1];
                count++;
            }
        }
        return count;
    }
}