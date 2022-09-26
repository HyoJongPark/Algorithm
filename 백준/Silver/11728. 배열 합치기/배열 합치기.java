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
        int[] num1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] num2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        StringBuilder sb = new StringBuilder();
        int point1 = 0, point2 = 0, index = 0;
        while (point1 < num1.length || point2 < num2.length) {
            if (point1 == num1.length) sb.append(num2[point2++]);
            else if (point2 == num2.length) sb.append(num1[point1++]);
            else if (num1[point1] <= num2[point2]) sb.append(num1[point1++]);
            else sb.append(num2[point2++]);
            sb.append(" ");
        }

        System.out.println(sb);
    }
}