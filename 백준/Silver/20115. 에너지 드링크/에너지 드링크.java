import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    static List<Integer> drink = new ArrayList<>();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int max = 0, maxIndex = 0;
        for (int i = 0; i < N; i++) {
            int amount = Integer.parseInt(st.nextToken());
            drink.add(amount);
            if (max < amount) {
                max = amount;
                maxIndex = i;
            }
        }

        drink.remove(maxIndex);
        double result = max;
        for (Integer amount : drink) {
            result += amount / 2.0;

        }
        System.out.println(result);
    }
}