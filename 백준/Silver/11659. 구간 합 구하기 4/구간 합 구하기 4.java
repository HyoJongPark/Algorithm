import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, PIV = 1;
	static int[] tree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		while (PIV < N) {
			PIV <<= 1;
		}
		tree = new int[2 * PIV];
		st = new StringTokenizer(br.readLine());
		for (int i = PIV; i < PIV + N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}
		
		initializeTree();
		StringBuilder sb = new StringBuilder();
		while (M-- != 0) {
			st = new StringTokenizer(br.readLine());
			int result = query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			sb.append(result).append("\n");
		}
		System.out.print(sb);
	}
	
	private static void initializeTree() {
		int start = PIV / 2, end = PIV;
		while (start != 0) {
			for (int i = start; i < end; i++) {
				tree[i] = tree[2 * i] + tree[2 * i + 1];
			}
			
			start /= 2;
			end /= 2;
		}
	}
	
	private static int query(int start, int end) {
		start += PIV - 1;
		end += PIV - 1;
		int sum = 0;
		while (start <= end) {
			if (start % 2 == 1) {
				sum += tree[start++];
			}
			if (end % 2 == 0) {
				sum += tree[end--];
			}
			
			start /= 2;
			end /= 2;
		}
		return sum;
	}
}