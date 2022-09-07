import java.util.LinkedList;
import java.util.Queue;

class Solution {
    boolean solution(String s) {
        int cnt = 0;
        char[] chars = s.toCharArray();

        Queue<Character> q = new LinkedList<>();
        while (true) {
            if (chars[cnt] == '(') {
                q.offer('(');
            } else {
                Character poll = q.poll();
                if (poll == null) return false;
            }
            cnt++;

            if (q.isEmpty() && cnt != chars.length && chars[cnt + 1] == '(') {
                cnt++;
                q.offer('(');
            }
            else if (q.isEmpty() && cnt == chars.length) return true;
            else if (cnt == chars.length) return false;
        }
    }
}