import java.util.PriorityQueue;

class Solution {

    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Time> pq = new PriorityQueue<>();
        for (String time : timetable) {
            pq.offer(new Time(time));
        }

        int lastBus = (n - 1) * t + 9 * 60;
        //마지막 버스 전까지 모두 보내기.
        for (int startTime = 9 * 60; startTime < lastBus; startTime += t) {
            int count = 0;
            while (!pq.isEmpty() && count < m) {
                if (pq.peek().time <= startTime) {
                    pq.poll();
                    count++;
                } else {
                    break;
                }
            }
        }

        //마지막 버스를 타기위해 언제 가야하는가?
        for (int i = 0; i < m - 1; i++) {
            if (pq.isEmpty() || pq.peek().time > lastBus) break;

            pq.poll();
        }

        int result;
        if (pq.isEmpty() || pq.peek().time > lastBus) {
            result = lastBus;
        } else {
            result = pq.peek().time - 1;
        } 
        return String.format("%02d:%02d", result / 60, result % 60);
    }

    static class Time implements Comparable<Time> {
        int time;

        public Time(String time) {
            String[] hourAndMinute = time.split(":");
            this.time = 60 * Integer.parseInt(hourAndMinute[0]) + Integer.parseInt(hourAndMinute[1]);
        }

        @Override
        public int compareTo(Time o) {
            return this.time - o.time;
        }
    }
}