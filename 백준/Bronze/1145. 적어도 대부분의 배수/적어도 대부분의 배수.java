import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[5];
        int cnt, result = 1;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 5; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        while (true) {
            cnt = 0;
            for (int i = 0; i < 5; i++) {
                if (result % arr[i] == 0) {
                    cnt++;
                }
            }
            if (cnt >= 3) break;

            result++;
        }

        System.out.println(result);
    }
}