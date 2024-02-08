import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int N;
    static int[] programmers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        programmers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0, right = N - 1;
        int cost = Integer.MIN_VALUE;
        while (left < right) {
            int currentCost;
            if (programmers[left] < programmers[right]) {
                currentCost = programmers[left] * (right - left++ - 1);
            } else {
                currentCost = programmers[right] * (right-- - left - 1);
            }
            cost = Math.max(currentCost, cost);
        }
        System.out.println(cost);
    }
}
