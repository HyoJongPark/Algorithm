class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String kNumber = transformToKNumber(n, k);
        String[] split = kNumber.split("0");
        for (String str : split) {
            if (!str.equals("") && isPrimeNumber(str)) {
                answer += 1;
            }
        }

        return answer;
    }

    private String transformToKNumber(int n, int k) {
        int current = n;
        String answer = "";
        while (current > 0) {
            answer = current % k + answer;
            current = current / k;
        }

        return answer;
    }

    private boolean isPrimeNumber(String str) {
        long num = Long.parseLong(str);
        if (num == 2 || num == 3) {
            return true;
        } else if (num % 2 == 0 || num < 2) {
            return false;
        }

        for (int i = 3; i < (int) Math.sqrt(num) + 1; i++) {
            if (num % i == 0) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(0, 3));
    }
}