import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] check;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        check = new int[N + 1];
        Arrays.fill(check, Integer.MAX_VALUE);

        DFS(0, N);
        System.out.println(check[1]);
    }

    private static void DFS(int count, int currentVal) {
        if (currentVal == 1) {
            check[currentVal] = count;
            return;
        }

        if (isValid(3, currentVal, count + 1)) {
            check[currentVal / 3] = count + 1;
            DFS(count + 1, currentVal / 3);
        }
        if (isValid(2, currentVal, count + 1)) {
            check[currentVal / 2] = count + 1;
            DFS(count + 1, currentVal / 2);
        }
        if (check[currentVal - 1] > count + 1) {
            check[currentVal - 1] = count + 1;
            DFS(count + 1, currentVal - 1);
        }
    }

    private static boolean isValid(int n, int val, int count) {
        return val % n == 0 && check[val / n] > count;
    }
}