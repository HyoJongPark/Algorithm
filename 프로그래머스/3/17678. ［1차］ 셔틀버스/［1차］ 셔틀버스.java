import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int[] time = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
            String[] splittedTime = timetable[i].split(":");
            
            time[i] = Integer.parseInt(splittedTime[0]) * 60 + Integer.parseInt(splittedTime[1]);
        }
        Arrays.sort(time);
        
        return findLatestTime(time, n, t, m);
    }
    
    //마지막 차의 승객이 가득 차면, 그 승객 바로 전 시간
    //아니라면, 마지막 차의 시간
    private String findLatestTime(int[] time, int busCount, int term, int numberOfPassenger) {
        int lastIdx = 0;
        
        for (int i = 0; i < busCount; i++) {
            int busTime = (9 * 60) + (term * i);
            int count = 0;
            
            for (; lastIdx < time.length;) {
                if (count == numberOfPassenger || time[lastIdx] > busTime) break;
                
                System.out.printf("%d, %d\n", i, time[lastIdx]);
                if (i == busCount - 1 && count == numberOfPassenger - 1) {
                    return String.format("%02d:%02d", (time[lastIdx] - 1) / 60, (time[lastIdx] - 1) % 60);
                }
                count++;
                lastIdx++;
            }
        }
        
        int lastBusTime = (9 * 60) + (term * (busCount - 1));
        return String.format("%02d:%02d", lastBusTime / 60, lastBusTime % 60);
    }
}