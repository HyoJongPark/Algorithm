import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    static int N;
    static int[] arr, lDp, rDp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        lDp = new int[N];
        rDp = new int[N];

        lDp[0] = 1;
        rDp[N - 1] = 1;
        lFind();
        rFind();

        int result = 1;
        for (int i = 0; i < N; i++) {
            result = Math.max(result, lDp[i] + rDp[i]);
        }
        System.out.println(result - 1);
    }

    private static void lFind() {
        for (int i = 1; i < N; i++) {
            int max = 0;

            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    max = Math.max(max, lDp[j]);
                }
            }

            lDp[i] = max + 1;
        }
    }

    private static void rFind() {
        for (int i = N - 2; i >= 0; i--) {
            int max = 0;

            for (int j = N - 1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    max = Math.max(max, rDp[j]);
                }
            }

            rDp[i] = max + 1;
        }
    }
}
