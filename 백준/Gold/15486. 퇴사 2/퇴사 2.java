import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.Math.max;

class Consult {
    int time;
    int value;

    public Consult(int time, int value) {
        this.time = time;
        this.value = value;
    }
}

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        Consult[] c = new Consult[N + 1];

        for (int i = 1; i <= N; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            c[i - 1] = new Consult(tmp[0], tmp[1]);
        }

        for (int t = 0; t < N; t++) {
            if (c[t].time + t <= N) dp[t + c[t].time] = max(dp[t + c[t].time], dp[t] + c[t].value);
            dp[t + 1] = max(dp[t], dp[t + 1]);
        }

        System.out.println(dp[N]);
    }
}