import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int minIdx = 0, count = 0;
        for (int i = people.length - 1; i >= minIdx; i--) {
            if (people[minIdx] + people[i] <= limit) {
                minIdx++;
            }
            count++;
        }
        return count;
    }
}