import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import static java.lang.Math.abs;
import static java.lang.Math.min;

class Main {
    static int N, M;
    static int answer = Integer.MAX_VALUE;
    static int[] selectedChickenHouse;
    static List<Point> chicken = new ArrayList<>();
    static List<Point> house = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 1) {
                    house.add(new Point(i, j));
                } else if (tmp == 2) {
                    chicken.add(new Point(i, j));
                }
            }
        }

        selectedChickenHouse = new int[M];
        DFS(0, 0);
        System.out.println(answer);
    }

    private static void DFS(int level, int crr) {
        if (level == M) {
            int distance = 0;
            for (Point h : house) {
                int tmp = Integer.MAX_VALUE;
                for (int chickenHouseNumber : selectedChickenHouse) {
                    tmp = Integer.min(tmp, chicken.get(chickenHouseNumber).calculateDistance(h.x, h.y));
                }
                distance += tmp;
            }
            answer = min(answer, distance);
            return;
        }

        for (int chickenHouseNumber = crr; chickenHouseNumber < chicken.size(); chickenHouseNumber++) {
            selectedChickenHouse[level] = chickenHouseNumber;
            DFS(level + 1, chickenHouseNumber + 1);
        }
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int calculateDistance(int x, int y) {
            return abs(x - this.x) + abs(y - this.y);
        }
    }
}