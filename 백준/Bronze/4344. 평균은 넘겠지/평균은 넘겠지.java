import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int C = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < C; i++) {
            int[] ints = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int N = ints[0];
            int avg = (Arrays.stream(ints).sum() - N) / N;

            float cnt = 0;
            for (int j = 1; j <= N; j++)
                if (ints[j] > avg) cnt++;
            float per = (cnt / N) * 100;
            sb.append(String.format("%.3f", per)).append("%\n");
        }
        System.out.println(sb);
    }
}