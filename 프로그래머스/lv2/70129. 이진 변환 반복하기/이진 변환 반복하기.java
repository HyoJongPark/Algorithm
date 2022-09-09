class Solution {
    public int[] solution(String s) {
        int[] answer = {0, 0};
        while (!s.equals("1")) {
            while (s.contains("0")){
                s = s.replaceFirst("0", "");
                answer[1]++;
            }
            s = Integer.toBinaryString(s.length());
            answer[0]++;
        }
        return answer;
    }
}