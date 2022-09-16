import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (a % 10 == 0) {
                sb.append(10).append("\n");
                continue;
            }

            int lastData = a % 10;
            for (int j = 0; j < b - 1; j++) {
                lastData = (lastData * a) % 10;
            }
            sb.append(lastData == 0 ? 10 : lastData).append("\n");
        }

        System.out.println(sb);
    }
}