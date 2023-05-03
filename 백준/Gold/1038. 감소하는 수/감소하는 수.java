import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<Long> nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new ArrayList<>();

        if (N <= 10) {
            System.out.println(N);
            System.exit(0);
        }

        for (int i = 0; i < 10; i++) {
            DFS(i, 1);
        }
        Collections.sort(nums);


        if (nums.size() - 1 < N) {
            System.out.println(-1);
        } else {
            System.out.println(nums.get(N));
        }
    }

    public static void DFS(long num, int idx) {
        //987654321이 최대
        if (idx > 10) {
            return;
        }

        nums.add(num);
        for (int i = 0; i < num % 10; i++) {
            DFS((num * 10) + i, idx + 1);
        }
    }
}
