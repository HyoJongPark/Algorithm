import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] split = br.readLine().split("-");

        int sum = Integer.MIN_VALUE, tmp = 0;
        for (int i = 0; i < split.length; i++) {
            tmp = 0;
            String[] nums = split[i].split("\\+");

            for (String num : nums) {
                tmp += Integer.parseInt(num);
            }

            if (sum == Integer.MIN_VALUE) sum = tmp;
            else sum -= tmp;
        }

        System.out.println(sum);
    }
}