import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] arrA, arrB;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arrA = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        arrB = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int indexA = 0, indexB = 0;
        StringBuilder sb = new StringBuilder();
        while (indexA < N || indexB < M) {
            if (indexA == N) {

            }

            if (indexB == M || (indexA != N && arrA[indexA] < arrB[indexB])) {
                sb.append(arrA[indexA++]);
            } else {
                sb.append(arrB[indexB++]);
            }
            sb.append(" ");
        }

        System.out.print(sb);
    }
}
