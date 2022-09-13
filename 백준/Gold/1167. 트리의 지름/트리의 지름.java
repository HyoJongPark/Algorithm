import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Point {
    int x;
    int distance;

    public Point(int x, int distance) {
        this.x = x;
        this.distance = distance;
    }
}

class Main {
    static int N, result = Integer.MIN_VALUE, lastPoint;
    static boolean[] check;
    static ArrayList<Point>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            tree[tmp[0]] = new ArrayList<Point>();
            for (int j = 1; j < tmp.length; j = j + 2) {
                if (tmp[j] != -1) {
                    tree[tmp[0]].add(new Point(tmp[j], tmp[j + 1]));
                }
            }
        }
        check = new boolean[N + 1];
        check[1] = true;
        DFS(1, 0);

        check = new boolean[N + 1];
        check[lastPoint] = true;
        DFS(lastPoint, 0);

        System.out.println(result);
    }

    private static void DFS(int current, int distance) {
        if (result < distance) {
            result = distance;
            lastPoint = current;
        }

        for (int i = 0; i < tree[current].size(); i++) {
            Point nextPoint = tree[current].get(i);
            if (!check[nextPoint.x]) {
                check[nextPoint.x] = true;
                DFS(nextPoint.x, distance + nextPoint.distance);
            }
        }
    }
}