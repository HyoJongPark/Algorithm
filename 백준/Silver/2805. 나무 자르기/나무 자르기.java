import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[] tree = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(tree);

        int left = 0, right = tree[N - 1], answer = 0;
        while (left < right) {
            int mid = (left + right) / 2;
            long cnt = 0;
            for (int i : tree) {
                if (i > mid) {
                    cnt += i - mid;
                }
            }

            if (cnt > M) {
                answer = mid;
                left = mid + 1;
            } else if (cnt < M) {
                right = mid;
            } else {
                answer = mid;
                break;
            }
        }
        System.out.println(answer);
    }
}