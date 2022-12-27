import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Lecture> lectures = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            lectures.add(new Lecture(startTime, endTime));
        }
        lectures.sort(Lecture::compareTo);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(lectures.get(0).endTime);
        for (int i = 1; i < N; i++) {
            Lecture currentLecture = lectures.get(i);
            if (pq.peek() <= currentLecture.startTime) {
                pq.poll();
            }
            pq.offer(currentLecture.endTime);
        }
        System.out.println(pq.size());
    }

    static class Lecture implements Comparable<Lecture> {
        int startTime;
        int endTime;

        public Lecture(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Lecture o) {
            if (this.startTime == o.startTime) {
                return this.endTime - o.endTime;
            }
            return this.startTime - o.startTime;
        }
    }
}