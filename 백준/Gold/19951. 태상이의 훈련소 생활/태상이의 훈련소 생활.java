import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int N, M;
    static int[] height, sumVal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        height = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        sumVal = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int k = Integer.parseInt(st.nextToken());

            sumVal[a] += k;
            sumVal[b + 1] -= k;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sumVal[i + 1] += sumVal[i];
            sb.append(height[i] + sumVal[i]).append("\n");
        }
        System.out.println(sb);
    }
}
