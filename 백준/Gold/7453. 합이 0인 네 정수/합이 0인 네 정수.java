import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[] A, B, C, D;
    static int[] sumAB, sumCD;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        B = new int[N];
        C = new int[N];
        D = new int[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        sumAB = new int[N * N];
        sumCD = new int[N * N];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sumAB[cnt] = A[i] + B[j];
                sumCD[cnt] = C[i] + D[j];
                cnt++;
            }
        }
        Arrays.sort(sumAB);

        long answer = 0;
        for (int CDValue : sumCD) {
            int lowerCount = lowerBound(-CDValue);
            int upperCount = upperBound(-CDValue);
            answer += upperCount - lowerCount;
        }

        System.out.println(answer);
    }

    private static int lowerBound(int targetValue) {
        int left = 0, right = sumAB.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (sumAB[mid] < targetValue) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

    private static int upperBound(int targetValue) {
        int left = 0, right = sumAB.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (sumAB[mid] <= targetValue) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
}