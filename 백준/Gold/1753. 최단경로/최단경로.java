import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point implements Comparable<Point> {
    int destination;
    int distance;

    public Point(int destination, int distance) {
        this.destination = destination;
        this.distance = distance;
    }

    @Override
    public int compareTo(Point o) {
        return this.distance - o.distance;
    }
}

class Main {
    static int N, M, start;
    static int[] distance;
    static ArrayList<ArrayList<Point>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        //initialize
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());
        distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Point(end, distance));
        }

        //find distance
        find();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (distance[i] != Integer.MAX_VALUE) sb.append(distance[i]).append("\n");
            else sb.append("INF").append("\n");
        }
        System.out.println(sb);
    }

    private static void find() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Point current = pq.poll();

            if (current.distance > distance[current.destination]) continue;
            for (Point destination : graph.get(current.destination)) {
                if (distance[destination.destination] > destination.distance + current.distance) {
                    distance[destination.destination] = destination.distance + current.distance;
                    pq.offer(new Point(destination.destination, destination.distance + current.distance));
                }
            }
        }
    }
}