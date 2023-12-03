import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N, K, cnt = 0;
    static int[] dp = new int[12];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp[1]=1;
        dp[2]=2;
        dp[3]=4;
        DFS(0,0);
        
        System.out.println(-1);
    }
    public static void DFS(int num, int level) {
        if(num > N) {
            return;
        }
        if(num == N) {
            if(++cnt == K) {
                StringBuilder sb = new StringBuilder();
                for(int i = 0; i < level - 1; i++) {
                    sb.append(dp[i]).append("+");
                }
                sb.append(dp[level - 1]);
                System.out.println(sb);
                System.exit(0);
            }
            return ;
        }

        for(int i = 1; i <= 3; i++) {
            dp[level] = i;
            DFS(num + i, level + 1);
        }
    }
}
