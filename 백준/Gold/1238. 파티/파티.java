import static java.lang.Math.max;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, X;
	static int[] distance;
	static List<List<Node>> tree = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		distance = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			tree.add(new ArrayList<>());
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			tree.get(Integer.parseInt(st.nextToken())).add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		int result = Integer.MIN_VALUE;
		dijkstra(X);
		int[] backHomeDistance = Arrays.copyOf(distance, N + 1);
		for (int i = 1; i <= N; i++) {
			if (i == X) {
				continue;
			}
			dijkstra(i);
			result = max(result, distance[X] + backHomeDistance[i]);
		}
		System.out.println(result);
	}
	
	private static void dijkstra(int startNode) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		Arrays.fill(distance, Integer.MAX_VALUE);
		pq.offer(new Node(startNode, 0));
		distance[startNode] = 0;
		
		while (!pq.isEmpty()) {
			Node currentNode = pq.poll();
			
			for (Node nextNode : tree.get(currentNode.nodeNo)) {
				if (distance[nextNode.nodeNo] > currentNode.distance + nextNode.distance) {
					distance[nextNode.nodeNo] = currentNode.distance + nextNode.distance;
					pq.offer(new Node(nextNode.nodeNo, distance[nextNode.nodeNo]));
				}
			}
		}
	}
	
	static class Node implements Comparable<Node> {
		int nodeNo;
		int distance;
		
		public Node(int nodeNo, int distance) {
			this.nodeNo = nodeNo;
			this.distance = distance;
		}
		
		@Override
		public int compareTo(Node o) {
			return this.distance - o.distance;
		}
	}
}