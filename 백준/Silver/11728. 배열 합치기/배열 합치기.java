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

        int pointerA = 0, pointerB = 0;
        StringBuilder sb = new StringBuilder();
        while (pointerA < N || pointerB < M) {
            if (pointerA == N || (pointerB != M && arrA[pointerA] > arrB[pointerB])) {
                sb.append(arrB[pointerB++]);
            } else {
                sb.append(arrA[pointerA++]);
            }
            sb.append(" ");
        }
        System.out.println(sb);
    }
}
