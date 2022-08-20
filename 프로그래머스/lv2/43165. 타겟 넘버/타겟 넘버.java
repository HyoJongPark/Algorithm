class Solution {

    public int solution(int[] numbers, int target) {
        int answer = 0;
        answer += DFS(0, 0, target, numbers);
        return answer;
    }

    private int DFS(int level, int current, int target, int[] numbers) {
        if (level == numbers.length && current == target) return 1;
        else if (level == numbers.length) return 0;

        return DFS(level + 1, current + numbers[level], target, numbers) +
                DFS(level + 1, current - numbers[level], target, numbers);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[]{1, 1, 1, 1, 1}, 3));
    }
}