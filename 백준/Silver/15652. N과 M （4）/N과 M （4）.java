import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] nums;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[M];
        DFS(0, 1);
        System.out.println(sb);
    }

    private static void DFS(int level, int current) {
        if (level == M) {
            for (int num : nums) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = current; i <= N; i++) {
            nums[level] = i;
            DFS(level + 1, i);
        }
    }
}