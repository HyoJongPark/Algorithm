import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, X;
    static int[] history;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        history = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int visitor = 0;
        for (int i = 0; i < X; i++) {
            visitor += history[i];
        }

        int left = 0, right = X - 1;
        int currentVisitor = visitor, count = 1;
        while (right < N - 1) {
            currentVisitor += history[++right] - history[left++];

            if (currentVisitor == visitor) {
                count++;
            } else if (currentVisitor > visitor) {
                visitor = currentVisitor;
                count = 1;
            }
        }

        if (visitor == 0) {
            System.out.println("SAD");
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(visitor).append("\n").append(count);
            System.out.println(sb);
        }
    }
}
