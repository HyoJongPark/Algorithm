import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Math.min;

class Bus {
    int destination;
    int price;

    public Bus(String destination, String price) {
        this.destination = Integer.parseInt(destination);
        this.price = Integer.parseInt(price);
    }
}

class Main {
    static int N, M, currentCity, targetCity;
    static ArrayList<ArrayList<Bus>> root = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) root.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            root.get(Integer.parseInt(st.nextToken())).add(new Bus(st.nextToken(), st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        currentCity = Integer.parseInt(st.nextToken());
        targetCity = Integer.parseInt(st.nextToken());

        System.out.println(findMinDistance());
    }

    private static int findMinDistance() {
        boolean[] check = new boolean[N + 1];
        int[] distance = new int[N + 1];
        for (int i = 1; i <= N; i++) Arrays.fill(distance, Integer.MAX_VALUE);
        distance[currentCity] = 0;

        while (currentCity != targetCity) {
            check[currentCity] = true;
            for (Bus bus : root.get(currentCity)) {
                if (!check[bus.destination]) {
                    distance[bus.destination] = min(bus.price + distance[currentCity], distance[bus.destination]);
                }
            }

            int tmp = Integer.MAX_VALUE;
            for (int i = 1; i <= N; i++) {
                if (!check[i] && distance[i] < tmp) {
                    tmp = distance[i];
                    currentCity = i;
                }
            }
        }
        return distance[targetCity];
    }
}