import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        int[] sum = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        numbers[0] = Integer.parseInt(st.nextToken());
        sum[0] = numbers[0];
        for (int i = 1; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + numbers[i];
        }

        long answer = 0;
        for (int i = 0; i < N - 1; i++) {
            answer += (long) numbers[i] * (sum[N - 1] - sum[i]);
        }
        System.out.println(answer);
    }
}