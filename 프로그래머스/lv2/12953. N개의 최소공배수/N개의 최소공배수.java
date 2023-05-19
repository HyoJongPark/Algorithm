class Solution {
    public int solution(int[] arr) {
        if(arr.length == 1) {
            return arr[0];
        }
        
        int g = gcd(arr[0], arr[1]); 
        int answer = (arr[0] * arr[1]) / g; 
        for(int i = 2; i < arr.length; i++) {
            g = gcd(answer, arr[i]);
            answer = (answer * arr[i]) / g;
        }
        return answer;
    }

    private static int gcd(int a, int b) {
        int r;
        if((r = a % b) == 0) {
            return b;
        } else {
            return gcd(b, r);
        }
    }
}