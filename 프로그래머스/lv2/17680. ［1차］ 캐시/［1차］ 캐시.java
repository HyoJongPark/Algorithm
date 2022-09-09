import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        if (cacheSize == 0) return cities.length * 5;

        Queue<String> cache = new LinkedList<>();
        int index = 0;
        String lowerCase;
        while (cache.size() != cacheSize && index != cities.length) {
            lowerCase = cities[index].toLowerCase();
            if (cache.contains(lowerCase)) {
                cache.remove(lowerCase);
                answer++;
            } else {
                answer += 5;
            }
            cache.offer(lowerCase);
            index++;
        }

        for (int i = index; i < cities.length; i++) {
            lowerCase = cities[i].toLowerCase();
            if (cache.contains(lowerCase)) {
                cache.remove(lowerCase);
                answer++;
            } else {
                cache.poll();
                answer += 5;
            }
            cache.offer(lowerCase);
        }
        return answer;
    }
}