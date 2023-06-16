import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class Main {

    static int N;
    static Map<Integer, Integer> cows = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int result = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()), position = Integer.parseInt(st.nextToken());

            if (cows.containsKey(num) && cows.get(num) != position) {
                result++;
            }
            cows.put(num, position);
        }
        System.out.println(result);
    }
}
