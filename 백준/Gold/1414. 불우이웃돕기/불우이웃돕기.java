import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

class Main {
    static int N, cost = 0;
    static List<Node>[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nodes = new List[N];
        for (int i = 0; i < N; i++) {
            nodes[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();

            for (int j = 0; j < N; j++) {
                if(input[j] == '0') continue;
                int inputTOInt;

                if (input[j] < 'a') {
                    inputTOInt = input[j] - 38;
                } else {
                    inputTOInt = input[j] - 'a' + 1;
                }

                cost += inputTOInt;
                nodes[i].add(new Node(j, inputTOInt));
                nodes[j].add(new Node(i, inputTOInt));
            }
        }

        if (searchNode()) {
            System.out.println(cost);
        } else {
            System.out.println(-1);
        }
    }

    private static boolean searchNode() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[N];

        pq.offer(new Node(0, 0));
        int count = 0;
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (!check[current.targetNode]) {
                check[current.targetNode] = true;
                cost -= current.cost;
                count++;

                for (Node next : nodes[current.targetNode]) {
                    if (!check[next.targetNode]) {
                        pq.offer(next);
                    }
                }
            }
        }

        return count == N;
    }

    static class Node implements Comparable<Node> {
        int targetNode;
        int cost;

        public Node(int targetNode, int cost) {
            this.targetNode = targetNode;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
