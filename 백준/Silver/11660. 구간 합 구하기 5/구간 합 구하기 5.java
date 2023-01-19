import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] arr, dp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][N + 1];
		dp = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + arr[i][j];
			}
		}
		StringBuilder sb = new StringBuilder();
		while (M-- != 0) {
			st = new StringTokenizer(br.readLine());
			int startX = Integer.parseInt(st.nextToken()), startY = Integer.parseInt(st.nextToken());
			int endX = Integer.parseInt(st.nextToken()), endY = Integer.parseInt(st.nextToken());

			int result = dp[endX][endY] - dp[endX][startY - 1] - dp[startX - 1][endY] + dp[startX - 1][startY - 1];	
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
}