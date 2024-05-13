import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M;
    static int[] jewel;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        jewel = new int[M];

        int left = 1, right = 1;
        for (int i = 0; i < M; i++) {
            jewel[i] = Integer.parseInt(br.readLine());
            right = Math.max(right, jewel[i]);
        }

        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = 0;

            for (int j : jewel) {
                count += j / mid;

                if (j % mid != 0) {
                    count++;
                }
            }

            if (count > N) {
                left = mid + 1;
            } else {
                right = mid - 1;
                result = mid;
            }
        }

        System.out.println(result);
    }
}
