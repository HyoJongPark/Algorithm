import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        long sum1 = Arrays.stream(queue1).sum(), sum2 = Arrays.stream(queue2).sum();
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        for (int i = 0; i < queue1.length; i++) {
            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }

        while (sum1 != sum2) {
            int crr;
            answer++;
            if (sum1 > sum2) {
                crr = q1.poll();
                q2.offer(crr);
                sum1 -= crr;
                sum2 += crr;
            } else {
                crr = q2.poll();
                q1.offer(crr);
                sum1 += crr;
                sum2 -= crr;
            }

            if (answer > (queue1.length + queue2.length) * 2) return -1;
        }

        return answer;
    }
}