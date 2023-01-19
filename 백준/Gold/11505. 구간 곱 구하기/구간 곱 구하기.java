import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
	static int N, M, K, PIV = 1;
	static long[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		while (PIV < N) {
			PIV <<= 1;
		}
		tree = new long[2 * PIV];
		for (int i = PIV; i < PIV + N; i++) {
			tree[i] = Integer.parseInt(br.readLine());
		}
		
		initializeTree();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M + K; i++) {
			st = new StringTokenizer(br.readLine());
			if (st.nextToken().equals("1")) {
				update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			} else {
				long result = query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				sb.append(result).append("\n");
			}
		}
		System.out.print(sb);
	}
	
	private static void initializeTree() {
		int start = PIV / 2, end = PIV;
		while (start != 0) {
			for (int i = start; i < end; i++) {
				tree[i] = (tree[i * 2] * tree[i * 2 + 1]) % 1_000_000_007;
			}
			
			start /= 2;
			end /= 2;
		}
	}
	
	private static void update(int index, int value) {
		index += PIV - 1;
		tree[index] = value;
		index /= 2;
		while (index != 0) {
			tree[index] = (tree[index * 2] * tree[index * 2 + 1]) % 1_000_000_007;
			index /= 2;
		}
	}
	
	private static long query(int start, int end) {
		long sum = 1;
		start += PIV - 1;
		end += PIV - 1;
		while (start <= end) {
			if (start % 2 == 1) {
				sum = (tree[start++] * sum) % 1_000_000_007;
			}
			if (end % 2 == 0) {
				sum = (tree[end--] * sum) % 1_000_000_007;
			}
			
			if (sum == 0) {
				return sum;
			}
			start /= 2;
			end /= 2;
		}
		return sum;
	}
}