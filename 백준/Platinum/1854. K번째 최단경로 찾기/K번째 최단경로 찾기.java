import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static List<Node>[] tree;
	static PriorityQueue<Integer>[] distance;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		tree = new ArrayList[N + 1];
		distance = new PriorityQueue[N + 1];
		for (int i = 0; i <= N; i++) {
			tree[i] = new ArrayList<>();
			distance[i] = new PriorityQueue<>(Collections.reverseOrder());
		}
		for (int i = 0; i < M; i++) {
			st= new StringTokenizer(br.readLine());
			tree[Integer.parseInt(st.nextToken())].add(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		dijkstra();
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (distance[i].size() == K) {
				sb.append(distance[i].poll()).append("\n");
			} else {
				sb.append(-1).append("\n");
			}
		}
		System.out.print(sb);
	}
	
	private static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		distance[1].add(0);
		pq.offer(new Node(1, 0));
		
		while(!pq.isEmpty()) {
			Node current = pq.poll();
			
			for (Node next : tree[current.nodeNo]) {
				if (distance[next.nodeNo].size() < K) {
					distance[next.nodeNo].offer(current.distance + next.distance);
					pq.offer(new Node(next.nodeNo, current.distance + next.distance));
				} else if (distance[next.nodeNo].peek() > current.distance + next.distance) {
					distance[next.nodeNo].poll();
					distance[next.nodeNo].offer(current.distance + next.distance);
					pq.offer(new Node(next.nodeNo, current.distance + next.distance));
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
		public int compareTo(Main.Node o) {
			// TODO Auto-generated method stub
			return this.distance - o.distance;
		}
	}
}