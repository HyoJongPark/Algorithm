import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] video;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int left = 0, right = 0;
        video = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            video[i] = Integer.parseInt(st.nextToken());
            right += video[i];
            left = Math.max(left, video[i]);
        }

        int result = binarySearch(left, right);
        System.out.println(result);
    }

    private static int binarySearch(int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = countNumberOfVideo(mid);

            if (count > M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private static int countNumberOfVideo(int mid) {
        int sum = 0, count = 0;
        for (int i = 0; i < N; i++) {
            if (sum + video[i] > mid) {
                sum = 0;
                count++;
            }
            sum += video[i];
        }

        return sum != 0 ? count + 1 : count;
    }
}
