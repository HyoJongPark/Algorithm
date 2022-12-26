import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Lesson> lessons = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            lessons.add(new Lesson(st.nextToken(), st.nextToken()));
        }
        lessons.sort(Lesson::compareTo);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(lessons.get(0).endTime);
        for (int i = 1; i < N; i++) {
            if (pq.peek() <= lessons.get(i).startTime) {
                pq.poll();
            }
            pq.offer(lessons.get(i).endTime);
        }
        System.out.println(pq.size());
    }

    static class Lesson implements Comparable<Lesson> {
        int startTime;
        int endTime;

        public Lesson(String startTime, String endTime) {
            this.startTime = Integer.parseInt(startTime);
            this.endTime = Integer.parseInt(endTime);
        }

        @Override
        public int compareTo(Lesson o) {
            if (this.startTime == o.startTime) {
                return this.endTime - o.endTime;
            }
            return this.startTime - o.startTime;
        }
    }
}