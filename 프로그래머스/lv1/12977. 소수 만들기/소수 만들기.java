class Solution {
    public int solution(int[] nums) {
        int result = 0;
        
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    result += isPrimeNumber(nums[i] + nums[j] + nums[k]);
                }
            }
        }
        return result;
    }
    
    private int isPrimeNumber(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return 0;
        }
        return 1;
    }
}