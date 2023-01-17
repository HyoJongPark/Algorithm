import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, S, D;
	static int[] distance;
	static boolean[][] check;
	static List<List<Integer>> visited;
	static List<List<Node>> tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		while ((N = Integer.parseInt(st.nextToken())) != 0 && (M = Integer.parseInt(st.nextToken())) != 0) {
			//initialize
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			distance = new int[N];
			visited = new ArrayList<>();
			tree = new ArrayList<>();
			check = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				tree.add(new ArrayList<>());
				visited.add(new ArrayList<>());
			}
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int U = Integer.parseInt(st.nextToken());
				int V = Integer.parseInt(st.nextToken());
				int P = Integer.parseInt(st.nextToken());
				tree.get(U).add(new Node(V, P));
			}
			
			findMinDistance();
			DFS(D);
			findMinDistance();
			if (distance[D] == Integer.MAX_VALUE) {
				sb.append("-1\n");
			} else {
				sb.append(distance[D]).append("\n");
			}			
			st = new StringTokenizer(br.readLine());
		}
		System.out.print(sb);
	}
	
	private static void findMinDistance() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(distance, Integer.MAX_VALUE);
		pq.offer(new Node(S, 0));
		distance[S] = 0;
		
		while(!pq.isEmpty()) {
			Node currentNode = pq.poll();
			
			for (Node nextNode : tree.get(currentNode.node)) {
				if (check[currentNode.node][nextNode.node]) {
					continue;
				}
				if (distance[nextNode.node] > currentNode.distance + nextNode.distance) {
					visited.get(nextNode.node).clear();
					visited.get(nextNode.node).add(currentNode.node);
					distance[nextNode.node] = currentNode.distance + nextNode.distance;
					pq.offer(new Node(nextNode.node, distance[nextNode.node]));
				} else if (distance[nextNode.node] == currentNode.distance + nextNode.distance) {
					visited.get(nextNode.node).add(currentNode.node);
				}
			}
		}
	}
	
	private static void DFS(int parentNode) {
		if (parentNode == S) {
			return;
		}
		
		for (int cildNode : visited.get(parentNode)) {
			if (!check[cildNode][parentNode]) {
				check[cildNode][parentNode] = true;
				DFS(cildNode);	
			}
		}
	}
	
	static class Node implements Comparable<Node>{
		int node;
		int distance;
		
		public Node(int node, int distance) {
			this.node = node;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}
	}
}