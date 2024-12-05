import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static long N, M, W, S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        System.out.println(calculate());
    }

    private static long calculate() {
        long result = (N + M) * W;

        long tmp = 0;
        if ((N + M) % 2 == 0) {
            tmp = Math.max(N, M) * S;
        } else {
            tmp = (Math.max(N, M) - 1) * S + W;
        }
        tmp = Math.min((Math.min(N, M)) * S + (Math.abs(N - M)) * W, tmp);
        return Math.min(result, tmp);
    }
}
