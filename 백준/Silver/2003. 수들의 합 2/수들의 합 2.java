import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N;
    static long M;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0, right = 0;
        long sum = arr[0], cnt = 0;
        while (left < N && right < N) {
            if (sum < M) {
                if (right == N - 1) {
                    break;
                }
                sum += arr[++right];
            } else if (sum > M) {
                if (left == right) {
                    if (right == N - 1) {
                        break;
                    }
                    sum += (arr[++right] - arr[left++]);
                } else {
                    sum -= arr[left++];
                }
            } else {
                cnt++;
                if (right == N - 1) {
                    break;
                }
                sum += (arr[++right] - arr[left++]);
            }
        }

        System.out.println(cnt);
    }
}