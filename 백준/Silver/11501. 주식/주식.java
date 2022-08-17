import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            long profit = 0;
            int N = Integer.parseInt(br.readLine());
            int[] prices = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt).toArray();
            int max = 0;

            for (int j = N - 1; j >= 0; j--) {
                if (prices[j] > max) {
                    max = prices[j];
                } else {
                    profit += max - prices[j];
                }
            }
            System.out.println(profit);
        }
    }
}