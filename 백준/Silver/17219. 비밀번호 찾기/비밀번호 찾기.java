import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {

    static int N, M;
    static HashMap<String, String> passwordBucket = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String site = st.nextToken();
            String password = st.nextToken();

            passwordBucket.put(site, password);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String site = br.readLine();

            sb.append(passwordBucket.get(site)).append("\n");
        }
        System.out.println(sb);
    }
}
