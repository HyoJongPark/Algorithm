import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, PIV = 1;
	static int[] maxTree, minTree;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		while (PIV < N) {
			PIV <<= 1;
		}
		maxTree = new int[PIV * 2 + 1];
		minTree = new int[PIV * 2 + 1];
		for (int i = PIV; i < PIV + N; i++) {
			maxTree[i] = Integer.parseInt(br.readLine());
			minTree[i] = maxTree[i];
		}
		initializeTree();
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int[] result = query(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			sb.append(result[0]).append(" ").append(result[1]).append("\n");
		}
		System.out.print(sb);
	}
	
	private static void initializeTree() {
		int start = PIV / 2, end = PIV;
		while (start != 0) {
			for (int i = start; i < end; i++) {
				maxTree[i] = Math.max(maxTree[2 * i], maxTree[2 * i + 1]);
				minTree[i] = Math.min(minTree[2 * i], minTree[2 * i + 1]);
			}
			
			start /= 2;
			end /= 2;
		}
	}
	
	private static int[] query(int start, int end) {
		//{min, max}
		int[] result = new int[2];
		result[0] = Integer.MAX_VALUE;
		result[1] = Integer.MIN_VALUE;
		start += PIV - 1;
		end += PIV - 1;
		while (start <= end) {
			if (start % 2 == 1) {
				result[0] = Math.min(result[0], minTree[start]);
				result[1] = Math.max(result[1], maxTree[start++]);
			}
			if (end % 2 == 0) {
				result[0] = Math.min(result[0], minTree[end]);
				result[1] = Math.max(result[1], maxTree[end--]);
			}
			
			start /= 2;
			end /= 2;
		}
		return result;
	}
}