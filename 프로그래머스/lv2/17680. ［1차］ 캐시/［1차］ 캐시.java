import java.util.*;

class Solution {

    static final int CACHE_HIT_TIME = 1;
    static final int CACHE_MISS_TIME = 5;

    public static void main(String[] args) {
        new Solution().solution(2, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"});
    }

    public int solution(int cacheSize, String[] cities) {
        int result = 0;
        HashSet<String> list = new HashSet<>();
        Queue<String> cache = new LinkedList<>();

        if (cacheSize == 0) {
            return cities.length * CACHE_MISS_TIME;
        }

        for (String city : cities) {
            city = city.toLowerCase();
            if (!list.contains(city)) {
                result += CACHE_MISS_TIME;
                if (cache.size() == cacheSize) {
                    String removed = cache.poll();
                    list.remove(removed);
                }
                list.add(city);
                cache.offer(city);
            } else {
                result += CACHE_HIT_TIME;
                cache.remove(city);
                cache.offer(city);
            }
        }
        return result;
    }
}