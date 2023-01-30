import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

    List<Node>[] tree;
    int[] distance;

    public int solution(int N, int[][] road, int K) {
        tree = new ArrayList[N + 1];
        distance = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] node : road) {
            tree[node[0]].add(new Node(node[1], node[2]));
            tree[node[1]].add(new Node(node[0], node[2]));
        }

        return dijkstra(1, K);
    }

    private int dijkstra(int startNode, int targetDistance) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[startNode] = 0;
        pq.offer(new Node(startNode, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            for (Node next : tree[current.nodeNo]) {
                if (distance[next.nodeNo] > current.distance + next.distance) {
                    distance[next.nodeNo] = current.distance + next.distance;
                    pq.offer(new Node(next.nodeNo, current.distance + next.distance));
                }
            }
        }

        int count = 0;
        for (int i = 1; i < distance.length; i++) {
            if (distance[i] <= targetDistance) count++;
        }
        return count;
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