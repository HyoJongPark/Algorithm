import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int B = Integer.parseInt(st.nextToken());
            char[] D = st.nextToken().toCharArray();
            sb.append(calculateMod(B - 1, D)).append("\n");
        }
        System.out.println(sb);
    }

    private static int calculateMod(int b, char[] d) {
        int sum = 0;
        for (char value :d){
            sum += value - '0';
        }
        return sum % b;
    }
}