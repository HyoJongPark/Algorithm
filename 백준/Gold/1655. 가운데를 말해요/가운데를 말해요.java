import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {
    static int N;
    static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (minHeap.size() == maxHeap.size()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }

            if (!minHeap.isEmpty() && !maxHeap.isEmpty() && minHeap.peek() < maxHeap.peek()) {
                int tmp = maxHeap.poll();
                maxHeap.offer(minHeap.poll());
                minHeap.offer(tmp);
            }

            sb.append(maxHeap.peek()).append("\n");
        }
        System.out.println(sb);
    }
}