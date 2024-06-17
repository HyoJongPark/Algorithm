import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    static int[] nums;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nums = new int[9];

        for (int i = 0; i < 9; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        dfs(0, new int[7], 0);
    }

    private static void dfs(int depth, int[] history, int total) {
        if (depth == 7 && total == 100) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 7; i++) {
                sb.append(nums[history[i]]).append("\n");
            }
            System.out.println(sb);

            System.exit(0);
        } else if (depth == 7 || total >= 100) {
            return;
        }

        for (int i = depth == 0 ? 0 : history[depth - 1] + 1; i < 9; i++) {
            history[depth] = i;
            dfs(depth + 1, history, total + nums[i]);
        }
    }
}
