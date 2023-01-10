import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int N, M;
    static int[] A, target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        M = Integer.parseInt(br.readLine());
        target = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(A);

        StringBuilder sb = new StringBuilder();
        for (int t : target) {
            sb.append(binarySearch(t)).append("\n");
        }
        System.out.println(sb);
    }

    private static int binarySearch(int target) {
        int left = 0, right = N - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (target > A[mid]) {
                left = mid + 1;
            } else if (target < A[mid]) {
                right = mid - 1;
            } else {
                return 1;
            }
        }
        return 0;
    }
}