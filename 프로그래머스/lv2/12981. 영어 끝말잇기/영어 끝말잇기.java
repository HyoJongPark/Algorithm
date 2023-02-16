import java.util.HashMap;
import java.util.Map;

class Solution {

    public static void main(String[] args) {
        new Solution().solution(2, new String[]{"hello", "one", "even", "never", "now", "world", "draw"});
    }

    public int[] solution(int n, String[] words) {
        Map<String, Boolean> check = new HashMap<>();

        int resultIndex = 0;
        boolean status = false;
        check.put(words[0], true);
        for (int i = 0; i < words.length - 1; i++) {
            char lastWord = words[i].toCharArray()[words[i].length() - 1];
            char firstWord = words[i + 1].toCharArray()[0];
            if (lastWord == firstWord) {
                if (check.getOrDefault(words[i + 1], false)) {
                    status = true;
                    resultIndex = i + 2;
                    break;
                }
                check.put(words[i], true);
            } else {
                status = true;
                resultIndex = i + 2;
                break;
            }
        }

        int[] result = new int[]{resultIndex % n, resultIndex / n};
        if (result[0] == 0 && status) result[0] = n;
        else if (result[0] != 0 && status) result[1] += 1;
        return result;
    }
}