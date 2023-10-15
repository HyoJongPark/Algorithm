import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] num;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        num = new int[N];

        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(num);

        int start = 0, end = 1;
        int result = Integer.MAX_VALUE;
        while (end < N) {
            int current = num[end] - num[start];
            if (current > M) {
                result = Math.min(result, current);
                start++;
            } else if (current < M) {
                end++;
            } else {
                result = M;
                break;
            }
        }
        System.out.println(result);
    }
}
