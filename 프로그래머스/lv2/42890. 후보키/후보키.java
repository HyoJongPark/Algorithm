import java.util.*;

class Solution {
    List<String> candidateKey = new ArrayList<>();
    
    public int solution(String[][] relation) {
        for (int i = 1; i <= relation[0].length; i++) {
            dfs(0, i, 0, new boolean[relation[0].length], relation);
        }
        
        return candidateKey.size();
    }
    
    private void dfs(int level, int maxDepth, int minIndex, boolean[] check, String[][] relation) {
        if (level == maxDepth) {
            HashSet<String> set = new HashSet<>();
            String key = "";
            
            for (String[] student: relation) {
                String relationString = "";
                
                for (int i = 0; i < check.length; i++) {
                    if (check[i]) {
                        relationString += student[i];
                        
                        if (key.length() != maxDepth) key += String.valueOf(i);
                    }
                }
                
                if (set.contains(relationString)) return;
                set.add(relationString);
            }
            
            for (int i = 0; i < candidateKey.size(); i++) {
                int count = 0;
                for (char k: key.toCharArray()) {
                    if (candidateKey.get(i).contains(String.valueOf(k))) count++;
                }
                if (count == candidateKey.get(i).length()) return;
            }
            candidateKey.add(key);
            return;
        }
        
        for (int i = minIndex; i < check.length; i++) {
            if (!check[i]) {
                check[i] = true;
                dfs(level + 1, maxDepth, i + 1, check, relation);
                check[i] = false;
            }
        }
    }
}