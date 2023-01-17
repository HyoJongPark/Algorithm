import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static long[] distance;
	static List<List<Node>> tree = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		distance = new long[N + 1];
		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			tree.get(A).add(new Node(B, C));
		}
		
		StringBuilder sb = new StringBuilder();
		if (!belman()) {
			System.out.println(-1);
			System.exit(0);
		} else {
			for (int i = 2; i <= N; i++) {
				if (distance[i] == Long.MAX_VALUE) {
					sb.append(-1).append("\n");
				} else {
					sb.append(distance[i]).append("\n");
				}
			}
		}
		System.out.print(sb);
	}
	
	private static boolean belman() {
		Arrays.fill(distance, Long.MAX_VALUE);
		distance[1] = 0;
		for (int i = 0; i < N - 1; i++) {
			for (int current = 1; current <= N; current++) {
				
				for (Node nextNode : tree.get(current)) {
					if (distance[current] != Long.MAX_VALUE && distance[nextNode.node] > distance[current] + nextNode.distance) {
						distance[nextNode.node] = distance[current] + nextNode.distance;
					}
				}
			}
		}
		
		for (int current = 1; current <= N; current++) {
			for (Node nextNode : tree.get(current)) {
				if (distance[current] != Long.MAX_VALUE && distance[nextNode.node] > distance[current] + nextNode.distance) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	static class Node {
		int node;
		int distance;
		
		public Node(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}
	}
}