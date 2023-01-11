    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;

    import static java.lang.Math.abs;
    import static java.lang.Math.min;

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class Main {
        static int N;
        static int M;
        static int answer = Integer.MAX_VALUE;
        static List<Point> chicken = new ArrayList<>();
        static List<Point> house = new ArrayList<>();

        private static void DFS(int level, int[] select, int crr) {
            if (level == M) {
                int tmp = 0;
                for (Point h : house) {
                    int distance = Integer.MAX_VALUE;
                    for (int i : select) {
                        Point c = chicken.get(i);
                        distance = Integer.min(distance, abs(h.x - c.x) + abs(h.y - c.y));
                    }
                    tmp += distance;
                }
                answer = min(tmp, answer);
            } else {
                for (int i = crr; i < chicken.size(); i++) {
                    select[level] = i;
                    DFS(level + 1, select, i+1);
                }
            }
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            N = sc.nextInt();
            M = sc.nextInt();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int tmp = sc.nextInt();
                    if (tmp == 1) {
                        house.add(new Point(i, j));
                    } else if (tmp == 2) {
                        chicken.add(new Point(i, j));
                    }
                }
            }

            DFS(0, new int[M], 0);
            System.out.println(answer);
        }
    }