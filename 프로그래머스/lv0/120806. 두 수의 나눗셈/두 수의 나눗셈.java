class Solution {

    public static void main(String[] args) {
        int solution = new Solution().solution(3, 2);
        System.out.println(solution);
    }

    public int solution(int num1, int num2) {
        if (num2 == 0) return 0;
        return (int) ((double) num1 / num2 * 1000);
    }
}