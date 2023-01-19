import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int W, H, G, E;
	static int[][] board;
	static long[][] distance;
	static List<Node> tree;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		while ((W = Integer.parseInt(st.nextToken())) != 0 && (H = Integer.parseInt(st.nextToken())) != 0) {
			tree = new ArrayList<>();
			board = new int[W][H];
			distance = new long[W][H];
			board[W - 1][H - 1] = 2;
			//Input: 묘비
			G = Integer.parseInt(br.readLine());
			for (; G > 0; G--) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
				board[x][y] = -1;
			}
			//Input: 귀신 구멍
			E = Integer.parseInt(br.readLine());
			for (; E > 0; E--) {
				st = new StringTokenizer(br.readLine());
				int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken());
				int x2 = Integer.parseInt(st.nextToken()), y2 = Integer.parseInt(st.nextToken());
				int t = Integer.parseInt(st.nextToken());
				board[x1][y1] = 1;
				tree.add(new Node(x1, y1, x2, y2, t));
			}
			
			findPath();
			if (!belman()) {
				sb.append("Never").append("\n");
			} else {
				if (distance[W - 1][H - 1] == Long.MAX_VALUE) {
					sb.append("Impossible").append("\n");
				} else {
					sb.append(distance[W - 1][H - 1]).append("\n");
				}
			}
			
			st = new StringTokenizer(br.readLine());
		}
		System.out.print(sb);
	}
	
	private static void findPath() {
		for (int i = 0; i < W; i++) {
			for (int j = 0; j < H; j++) {
				if (board[i][j] != 0) {
					continue;
				}
				for (int k = 0; k < 4; k++) {
					int nextX = i + dx[k];
					int nextY = j + dy[k];
					
					if (isValid(nextX, nextY)) {
						tree.add(new Node(i, j, nextX, nextY, 1));
					}
				}
			}
		}
	}
	
	private static boolean belman() {
		initializeDistance();
		distance[0][0] = 0;
		for (int t = 0; t < W * H - 1; t++) {
			for (Node node : tree) {
				if (distance[node.startX][node.startY] != Long.MAX_VALUE 
						&& distance[node.endX][node.endY] > distance[node.startX][node.startY] + node.distance) {
					distance[node.endX][node.endY] = distance[node.startX][node.startY] + node.distance;
				}
			}
		}
		
		for (int t = 0; t < W * H - 1; t++) {
			for (Node node : tree) {
				if (distance[node.startX][node.startY] != Long.MAX_VALUE && distance[node.endX][node.endY] > distance[node.startX][node.startY] + node.distance) {
					return false;
				}
			}
		}
		return true;
	}
	
	private static boolean isValid(int nextX, int nextY) {
		return 0 <= nextX && nextX < W && 0 <= nextY && nextY < H && board[nextX][nextY] != -1;
	}
	
	private static void initializeDistance() {
		for (int i = 0; i < W; i++) {
			Arrays.fill(distance[i], Long.MAX_VALUE);
		}
	}
	
	static class Node {
		int startX, startY;
		int endX, endY;
		int distance;
		
		public Node(int startX, int startY, int endX, int endY, int distance) {
			this.startX = startX;
			this.startY = startY;
			this.endX = endX;
			this.endY = endY;
			this.distance = distance;
		}
	}
}
