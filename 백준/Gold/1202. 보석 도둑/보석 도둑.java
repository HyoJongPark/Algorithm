import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Jewel> jewels = new ArrayList<>();
        List<Integer> bags = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            jewels.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        for (int i = 0; i < K; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }
        jewels.sort(Jewel::compareTo);
        bags.sort(Integer::compareTo);

        long result = 0;
        int index = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < K; i++) {
            int currentBagWeight = bags.get(i);
            while (index < jewels.size() && jewels.get(index).weight <= currentBagWeight) {
                pq.offer(jewels.get(index++).value);
            }

            if (!pq.isEmpty()) {
                result += pq.poll();
            }
        }
        System.out.println(result);
    }

    private static class Jewel implements Comparable<Jewel> {
        int weight;
        int value;

        public Jewel(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jewel o) {
            return this.weight- o.weight;
        }
    }
}
