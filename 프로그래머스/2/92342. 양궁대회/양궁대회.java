class Solution {
    static int maxScore = Integer.MIN_VALUE;
    static int[] info, result = {-1};
    
    public int[] solution(int n, int[] info) {
        this.info = info;
        int score = 0;
        for (int i = 0; i < info.length; i++) {
            if (info[i] != 0) score += (10 - i);
        }
        
        dfs(0, score, 10, n, new int[11]);
        return result;
    }
    
    private void dfs(int myScore, int otherScore, int lastIdx, int numberOfArrow, int[] history) {
        if (myScore > otherScore && (myScore - otherScore) > maxScore) {
            maxScore = myScore - otherScore;
            result = new int[11];
            System.out.println(myScore);
            
            System.arraycopy(history, 0, result, 0, history.length);
            result[10] = numberOfArrow;
        }
        if (numberOfArrow == 0) return;
        
        for (int i = lastIdx - 1; i >= 0; i--) {
            if (info[i] >= numberOfArrow) continue;
            
            history[i] = info[i] + 1;//9+6+5+4 ; 9+8+6
            if (info[i] == 0) {
                dfs(myScore + (10 - i), otherScore, i, numberOfArrow - history[i], history);    
            } else {
                dfs(myScore + (10 - i), otherScore - (10 - i), i, numberOfArrow - history[i], history);    
            }
            history[i] = 0;
        } //9, 8, 7, 6, 5(35) \\ 10, 9, 8, 6, 5(38)
    }
}