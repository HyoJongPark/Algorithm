import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int num : works) {
            pq.offer(num);
        }
        
        for (int i = 0; i < n; i++) {
            if (pq.isEmpty()) break;
            
            int next = pq.poll() - 1;
            if (next != 0) pq.offer(next);
        }

        long sum = 0;
        while (!pq.isEmpty()) {
            int current = pq.poll();
            
            sum += Math.pow(current, 2);
        }
        return sum;
    }
}