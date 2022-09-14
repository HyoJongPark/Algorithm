import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Main {
    static int N, maxDistance = Integer.MIN_VALUE, lastNode;
    static boolean[] check;
    static ArrayList<Point>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) tree[i] = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            tree[tmp[0]].add(new Point(tmp[1], tmp[2]));
            tree[tmp[1]].add(new Point(tmp[0], tmp[2]));
        }

        check = new boolean[N + 1];
        check[1] = true;
        DFS(1, 0);

        check = new boolean[N + 1];
        check[lastNode] = true;
        DFS(lastNode, 0);

        System.out.println(maxDistance);
    }

    private static void DFS(int currentNode, int distance) {
        if (distance > maxDistance) {
            maxDistance = distance;
            lastNode = currentNode;
        }

        for (int i = 0; i < tree[currentNode].size(); i++) {
            Point nextNode = tree[currentNode].get(i);
            if (!check[nextNode.x]) {
                check[nextNode.x] = true;
                DFS(nextNode.x, distance + nextNode.distance);
            }
        }
    }

    static class Point {
        int x;
        int distance;

        public Point(int x, int distance) {
            this.x = x;
            this.distance = distance;
        }
    }
}