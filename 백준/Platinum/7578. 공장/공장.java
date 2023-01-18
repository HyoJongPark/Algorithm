import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main{
	static int N, PIV = 1;
	static long[] A, tree;
	static Map<Long, Integer> B = new HashMap<>(); 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		while (PIV < N) {
			PIV <<= 1;
		}
		tree = new long[2 * PIV];
		A = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			B.put(Long.parseLong(st.nextToken()), i);
		}
		
		long result = 0;
		for (int i = 0; i < N; i++) {
			int index = B.get(A[i]);
			update(index);
			result += query(index + 1);
		}
		System.out.print(result);
	}
	
	private static void update(int index) {
		index += PIV;
		tree[index] = 1;
		index /= 2;
		while (index != 0) {
			tree[index] = tree[index * 2 + 1] + tree[index * 2];
			index /= 2;
		}
	}
	
	private static long query(int start) {
		start += PIV;
		int end = PIV + N - 1;
		long sum = 0;
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