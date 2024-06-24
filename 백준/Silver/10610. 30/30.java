import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Main {

    static char[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = br.readLine().toCharArray();
        Arrays.sort(arr);

        if (arr[0] != '0') {
            exit();
        }

        int sum = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = arr.length - 1; i >= 0; i--) {
            int num = arr[i] - '0';
            sum += num;

            sb.append(num);
        }

        if (sum % 3 != 0) {
            exit();
        }
        System.out.println(sb);
    }

    private static void exit() {
        System.out.println(-1);
        System.exit(0);
    }
}
