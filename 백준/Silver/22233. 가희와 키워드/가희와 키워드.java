import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(",");

            for (String s : input) {
                set.remove(s);
            }
            sb.append(set.size()).append("\n");
        }
        System.out.print(sb);
    }
}
