class Solution {
    public String solution(String s) {
        char[] chars = s.toCharArray();

        boolean isNewCase = true;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                sb.append(" ");
                isNewCase = true;
            } else if (isNewCase && chars[i] != ' ') {
                sb.append(Character.toUpperCase(chars[i]));
                isNewCase = false;
            } else {
                sb.append(Character.toLowerCase(chars[i]));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("3people Unfollowed  Me"));
    }
}