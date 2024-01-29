import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
    static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            HashMap<String, Integer> map = new HashMap<>();

            //make map
            while (N-- > 0) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                st.nextToken();

                String category = st.nextToken();
                map.put(category, map.getOrDefault(category, 0) + 1);
            }

            //calculate
            int result = 1;
            for (Integer value : map.values()) {
                result *= (value + 1);
            }
            sb.append(result - 1).append("\n");
        }
        System.out.println(sb);
    }
}
