import java.util.*;

class Solution {
    
    int answer = 0;
    
    public int solution(String[] user_ids, String[] banned_ids) {
        dfs(0, user_ids, banned_ids, new HashSet<>(), new int[banned_ids.length]);
        return answer;
    }
    
    private void dfs(int depth, String[] user_ids, String[] banned_ids, HashSet<String> check, int[] history) {
        if (depth == banned_ids.length) {
            int[] copy = Arrays.copyOf(history, history.length);
            Arrays.sort(copy);
            String historyString = "";
            for (int h: copy) {
                historyString += h;
            }
            
            if (check.contains(historyString)) return;
            check.add(historyString);
            answer++;
            return;
        }
        
        for (int i = 0; i < user_ids.length; i++) {
            if (check.contains(user_ids[i]) || banned_ids[depth].length() != user_ids[i].length()) continue;
            
            if (isBanndedId(banned_ids[depth], user_ids[i])) {
                check.add(user_ids[i]);
                history[depth] = i;
                dfs(depth + 1, user_ids, banned_ids, check, history);
                check.remove(user_ids[i]);
            }
        }
    }
    
    private boolean isBanndedId(String banndedId, String userId) {
        for (int i = 0; i < banndedId.length(); i++) {
            if (banndedId.charAt(i) == '*') continue;
            if (banndedId.charAt(i) != userId.charAt(i)) return false;
        }
        return true;
    }
}