import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int N, K;
    static int[] sensor;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        sensor = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        if (N == K) {
            System.out.println(0);
            System.exit(0);
        }

        int[] distance = new int[N - 1];
        for (int i = 1; i < N; i++) {
            distance[i - 1] = sensor[i] - sensor[i - 1];
        }
        Arrays.sort(distance);

        int result = 0;
        for (int i = 0; i < N - K; i++) {
            result += distance[i];
        }
        System.out.println(result);
    }
}
