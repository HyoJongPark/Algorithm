import java.util.*;

class Solution {

    HashMap<Integer, Integer> box = new HashMap<>();

    public int solution(int k, int[] tangerine) {
        for (int i = 0; i < tangerine.length; i++) {
            box.put(tangerine[i], box.getOrDefault(tangerine[i], 0) + 1);
        }

        return countTangerine(k);
    }

    private int countTangerine(int k) {
        Integer[] v = box.values().toArray(new Integer[box.size()]);
        Arrays.sort(v);
        
        int count = 1;
        for (int i = v.length - 1; i >= 0; i--) {
            if ((k -= v[i]) <= 0) break;
            
            count++;
        }
        return count;
    }
}