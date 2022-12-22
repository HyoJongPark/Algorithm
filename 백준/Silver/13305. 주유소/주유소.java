import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static List<Integer> distances = new ArrayList<>();
    static List<Integer> gasPrice = new ArrayList<>();
    static int N, currentPrice;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()) - 1;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            distances.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        currentPrice = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            gasPrice.add(Integer.parseInt(st.nextToken()));
        }

        System.out.println(findMinimumCost());
    }

    private static long findMinimumCost() {
        int currentPosition = 0;
        long totalCost = 0;
        while (currentPosition < N) {
            for (int nextPosition = currentPosition; nextPosition < N; nextPosition++) {
                if (currentPrice > gasPrice.get(nextPosition) || nextPosition == N - 1) {
                    long totalDistance = calculateTotalDistance(currentPosition, nextPosition + 1);
                    currentPosition = nextPosition + 1;
                    totalCost += currentPrice * totalDistance;
                    currentPrice = gasPrice.get(nextPosition);
                    break;
                }
            }
        }
        return totalCost;
    }

    private static long calculateTotalDistance(int currentPosition, int nextPosition) {
        long totalDistance = 0L;
        for (int i = currentPosition; i < nextPosition; i++) {
            totalDistance += distances.get(i);
        }
        return totalDistance;
    }
}