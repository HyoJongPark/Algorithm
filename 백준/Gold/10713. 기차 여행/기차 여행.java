import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] city, count;
    static int[][] trainCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        city = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        count = new int[N];
        trainCost = new int[N][3];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());

            trainCost[i][0] = Integer.parseInt(st.nextToken());
            trainCost[i][1] = Integer.parseInt(st.nextToken());
            trainCost[i][2] = Integer.parseInt(st.nextToken());
        }

        count();

        long psum = 0, result = 0;
        for (int i = 0; i < N - 1; i++) {
            psum += count[i];

            result += Math.min(psum * trainCost[i][0], psum * trainCost[i][1] + trainCost[i][2]);
        }
        System.out.println(result);
    }

    private static void count() {
        for (int i = 1; i < M; i++) {
            int start = city[i - 1] - 1;
            int end = city[i] - 1;

            if (start > end) {
                int tmp = start;
                start = end;
                end = tmp;
            }

            count[start]++;
            count[end]--;
        }
    }
}
