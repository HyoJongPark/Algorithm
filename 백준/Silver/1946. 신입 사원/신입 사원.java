import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int M = Integer.parseInt(br.readLine());
            int count = 1;
            int[] scores = new int[M + 1];
            for (int j = 0; j < M; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                scores[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
            }

            int beforeValue = scores[1];
            for (int j = 2; j <= M; j++) {
                if (scores[j] < beforeValue) {
                    beforeValue = scores[j];
                    count++;
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}