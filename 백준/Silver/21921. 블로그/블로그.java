import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, X;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        arr = new int[N];

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if (i < X) {
                sum += arr[i];
            }
        }

        int left = 0, right = X - 1, count = 1;
        int max = sum;
        while (right < N - 1) {
            sum += arr[++right] - arr[left++];

            if (sum == max) {
                count++;
            } else if (sum > max) {
                max = sum;
                count = 1;
            }
        }

        if (max == 0) {
            System.out.print("SAD");
        } else {
            System.out.printf("%d\n%d", max, count);
        }
    }
}
