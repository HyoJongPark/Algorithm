import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] countBoard = new int[7][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            countBoard[Y][S]++;
        }

        int result = 0;
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 2; j++) {
                result += countBoard[i][j] / K;
                if (countBoard[i][j] % K != 0) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }
}