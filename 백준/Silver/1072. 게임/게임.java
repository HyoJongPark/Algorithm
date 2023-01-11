import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int Z = (int) (((double) 100 * M / N));

        int left = 1, right = (int) 1e9;
        int answer = 0;
        if (Z >= 99) {
            System.out.println(-1);
            System.exit(0);
        }
        while (left <= right) {
            int mid = (left + right) / 2;
            int nextValue = (int) (((long) 100 * (M + mid) / (N + mid)));

            if (nextValue > Z) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }
}