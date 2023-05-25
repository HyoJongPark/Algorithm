import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, W, L;
    static Queue<Integer> truck = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            truck.offer(Integer.parseInt(st.nextToken()));
        }

        System.out.println(findResult());
    }

    static int findResult() {
        Queue<Integer> q = new LinkedList<>();
        int time = 0, weight = 0;
        for (int i = 0; i < W; i++) {
            q.add(0);
        }

        while (!q.isEmpty()) {
            time++;
            weight -= q.poll();
            if (!truck.isEmpty()) {
                if (truck.peek() + weight <= L) {
                    weight += truck.peek();
                    q.offer(truck.poll());
                } else {
                    q.offer(0);
                }
            }
        }

        return time;
    }
}
