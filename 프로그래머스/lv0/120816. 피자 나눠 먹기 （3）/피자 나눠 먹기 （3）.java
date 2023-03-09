class Solution {
    public int solution(int slice, int n) {
        int answer = 1;
        int current = slice;
        while (current / n < 1) {
            current += slice;
            answer++;
        }
        return answer;
    }
}