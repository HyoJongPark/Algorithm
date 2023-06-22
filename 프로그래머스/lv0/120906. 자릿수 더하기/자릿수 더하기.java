class Solution {
    public int solution(int n) {
        int answer = 0;
        for (int i = 10; i < n; n /= 10) {
            answer += n % i;
        }
        return answer + n;
    }
}