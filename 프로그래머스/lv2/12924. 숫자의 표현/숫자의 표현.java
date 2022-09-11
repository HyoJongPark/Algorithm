class Solution {

    public int solution(int n) {
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            answer += DFS(i, i, n);
        }

        return answer;
    }

    private int DFS(int current, int sum, int target) {
        if (sum == target) return 1;
        else if (sum > target) return 0;

        return DFS(current + 1, sum + (current + 1), target);
    }
}