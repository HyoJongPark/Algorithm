import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {

    static HashMap<String, Integer> dictionary = new HashMap<>();

    static {
        for (char c = 'A'; c <= 'Z'; c++) {
            dictionary.put(String.valueOf(c), c - 'A' + 1);
        }
    }

    public int[] solution(String msg) {
        List<Integer> result = new ArrayList<>();
        int index = 0;

        while (index + 1 < msg.length()) {
            String cur = "", prev = String.valueOf(msg.charAt(index));

            for (int i = 1; index + i <= msg.length(); i++) {
                cur = msg.substring(index, index + i);

                if (!dictionary.containsKey(cur)) {
                    dictionary.put(cur, dictionary.size() + 1);
                    break;
                }
                prev = cur;
            }

            result.add(dictionary.get(prev));
            index += prev.length();
        }

        if (index == msg.length() - 1) {
            result.add(dictionary.get(String.valueOf(msg.charAt(index))));
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
