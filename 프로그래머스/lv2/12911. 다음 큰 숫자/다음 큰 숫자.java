class Solution {
    public int solution(int n) {
        int answer = n + 1;
        int initCount = Integer.bitCount(n);
        
        while (!isNextBigNumber(initCount, answer)) {
            answer++;
        }
        return answer;
    }

    private boolean isNextBigNumber(int initCount, int nextNum) {
        return initCount == Integer.bitCount(nextNum);
    }
}