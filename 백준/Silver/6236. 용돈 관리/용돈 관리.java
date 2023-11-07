import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N];

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            board[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, board[i]);
        }

        int result = binarySearch(max);
        System.out.println(result);
    }

    private static int binarySearch(int max) {
        int left = max, right = 10000 * 100000;
        int result = -1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (isValid(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    private static boolean isValid(int money) {
        int count = 1, current = money;

        for (int i = 0; i < N; i++) {
            if (current - board[i] < 0) {
                count++;
                current = money - board[i];
            } else {
                current -= board[i];
            }
        }
        return M >= count;
    }
}
