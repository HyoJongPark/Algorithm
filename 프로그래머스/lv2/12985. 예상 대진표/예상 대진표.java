class Solution {
    public int solution(int n, int a, int b) {
        //swap
        if (a > b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        
        return playGameWhileMatch(a, b);
    }
    
    private int playGameWhileMatch(int a, int b) {
        int count = 1;
        while (!(a % 2 == 1 && a + 1 == b)) {
            a = (a / 2) + (a % 2);
            b = (b / 2) + (b % 2);

            count++;
        }
        
        return count;
    }
}