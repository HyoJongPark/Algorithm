import java.util.Arrays;

class Solution {
    public int solution(int[][] scores) {
        int scoreA = scores[0][0];
        int scoreB = scores[0][1];
        
        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });

        int maxScore = 0, count = 1;
        for (int i = 0; i < scores.length; i++) {
            if (maxScore > scores[i][1]) {
                if (scores[i][0] == scoreA && scores[i][1] == scoreB) {
                    return -1;   
                }
            } else {
                maxScore = Math.max(maxScore, scores[i][1]);
                
                if (scoreA + scoreB < scores[i][0] + scores[i][1]) {
                    count++;
                }
            }
        }
        
        return count;
    }
}