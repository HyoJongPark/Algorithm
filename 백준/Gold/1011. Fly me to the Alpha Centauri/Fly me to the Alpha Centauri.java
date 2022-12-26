import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int startPoint = Integer.parseInt(st.nextToken());
            int endPoint = Integer.parseInt(st.nextToken());

            int distance = endPoint - startPoint;
            int max = (int) Math.sqrt(distance);

            if (max == Math.sqrt(distance)) {
                sb.append(max * 2 - 1).append('\n');
            } else if (distance <= max * max + max) {
                sb.append(max * 2).append('\n');
            } else {
                sb.append(max * 2 + 1).append('\n');
            }
        }
        System.out.println(sb);
    }
}