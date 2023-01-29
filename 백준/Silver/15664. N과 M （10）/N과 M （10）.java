import java.util.*;
import java.io.*;

public class Main {

    static int N, M;
    static int[] nums, result;
    static boolean[] check;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result = new int[M];
        check = new boolean[N];

        Arrays.sort(nums);
        DFS(0, 0);
        System.out.println(sb);
    }

    static void DFS(int current, int level) {
        if (level == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int beforeNum = -1;
        for (int i = current; i < N; i++) {
            int currentNum = nums[i];
            if (!check[i] && beforeNum != currentNum) {
                check[i] = true;
                beforeNum = currentNum;
                result[level] = currentNum;
                DFS(i + 1, level + 1);
                check[i] = false;
            }
        }
    }
}