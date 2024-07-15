import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int N;
    static int[] rope;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        rope = new int[N];

        for (int i = 0; i < N; i++) {
            rope[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(rope);
        int max = rope[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            int currentWeight = rope[i] * (N - i);

            max = Math.max(max, currentWeight);
        }
        System.out.println(max);
    }
}
