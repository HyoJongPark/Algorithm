import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int[] parents;
    static Planet[] planets;
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        planets = new Planet[N];
        parents = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            parents[i] = i;
            planets[i] = new Planet(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()));
        }

        sortAndMakeEdge();

        int count = 0, cost = 0;
        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (union(current.st, current.en)) {
                cost += current.cost;

                if (++count == N - 1) {
                    break;
                }
            }
        }
        System.out.println(cost);
    }

    private static void sortAndMakeEdge() {
        Arrays.sort(planets, Comparator.comparingInt(p -> p.x));
        for (int i = 0; i < N - 1; i++) {
            int cost = Math.abs(planets[i].x - planets[i + 1].x);

            pq.offer(new Node(planets[i].planetNo, planets[i + 1].planetNo, cost));
        }

        Arrays.sort(planets, Comparator.comparingInt(p -> p.y));
        for (int i = 0; i < N - 1; i++) {
            int cost = Math.abs(planets[i].y - planets[i + 1].y);

            pq.offer(new Node(planets[i].planetNo, planets[i + 1].planetNo, cost));
        }

        Arrays.sort(planets, Comparator.comparingInt(p -> p.z));
        for (int i = 0; i < N - 1; i++) {
            int cost = Math.abs(planets[i].z - planets[i + 1].z);

            pq.offer(new Node(planets[i].planetNo, planets[i + 1].planetNo, cost));
        }
    }

    private static boolean union(int st, int en) {
        st = find(st);
        en = find(en);

        if (st == en) {
            return false;
        }
        parents[st] = en;
        return true;
    }

    private static int find(int planetNo) {
        if (parents[planetNo] == planetNo) {
            return planetNo;
        }
        return parents[planetNo] = find(parents[planetNo]);
    }

    static class Planet {
        int planetNo;
        int x, y, z;

        public Planet(int planetNo, int x, int y, int z) {
            this.planetNo = planetNo;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Node implements Comparable<Node> {
        int st, en;
        int cost;

        public Node(int st, int en, int cost) {
            this.st = st;
            this.en = en;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
