import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, B;
    static int[] trafficLight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        trafficLight = new int[N+1];

        for (int i = 0; i < B; i++) {
            trafficLight[Integer.parseInt(br.readLine())] = 1;
        }

        //initialize
        for (int i = 2; i <= K; i++) {
            trafficLight[i] += trafficLight[i - 1];
        }

        int result = trafficLight[K];
        for (int i = K + 1; i <= N; i++) {
            trafficLight[i] += trafficLight[i - 1];
            result = Math.min(result, trafficLight[i]  - trafficLight[i - K]);
        }
        System.out.println(result);
    }

}
