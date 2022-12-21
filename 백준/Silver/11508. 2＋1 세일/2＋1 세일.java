import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Main {

    static List<Integer> milkPrices = new ArrayList<>();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            milkPrices.add(Integer.parseInt(br.readLine()));
        }
        milkPrices.sort(Comparator.reverseOrder());

        long answer = 0;
        for (int i = 0; i < N; i++) {
            if (i % 3 != 2) {
                answer += milkPrices.get(i);
            }
        }
        System.out.println(answer);
    }
}