import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] psum = new int[N + 1][M + 1];
        st = new StringTokenizer(br.readLine());
        psum[1][1] = Integer.parseInt(st.nextToken());
        for (int j = 2; j <= M; j++) {
            psum[1][j] = Integer.parseInt(st.nextToken()) + psum[1][j - 1];
        }

        for (int i = 2; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            psum[i][1] = Integer.parseInt(st.nextToken()) + psum[i - 1][1];
            for (int j = 2; j <= M; j++) {
                psum[i][j] = Integer.parseInt(st.nextToken()) + psum[i][j - 1] + psum[i - 1][j] - psum[i - 1][j - 1];
            }
        }

        K = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            sb.append(psum[x][y] - psum[i - 1][y] - psum[x][j - 1] + psum[i - 1][j - 1]).append("\n");
        }
        System.out.print(sb);
    }
}
