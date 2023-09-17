import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int M, N, max = Integer.MIN_VALUE;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Integer.max(max, arr[i]);
        }

        int result = binarySearch();
        if (result == Integer.MIN_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(result);
        }
        System.out.println();
    }

    private static int binarySearch() {
        int left = 1, right = max;
        int result = Integer.MIN_VALUE;
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = calculateCount(mid);

            if (count >= M) {
                left = mid + 1;
                result = Integer.max(result, mid);
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    private static int calculateCount(int mid) {
        int count = 0;
        for (int num : arr) {
            count += num / mid;
        }
        return count;
    }
}