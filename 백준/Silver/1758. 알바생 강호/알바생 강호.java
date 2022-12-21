import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static List<Integer> customers = new ArrayList<>();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            customers.add(Integer.parseInt(br.readLine()));
        }
        customers.sort(Comparator.reverseOrder());

        long answer = 0;
        for (int i = 0; i < N; i++) {
            int currentValue = customers.get(i) - i;
            if (currentValue > 0) {
                answer += currentValue;
            } else {
                break;
            }
        }
        System.out.println(answer);
    }
}