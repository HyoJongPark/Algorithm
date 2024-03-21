import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int N, M, K;
    static int[][] board, nutrient;
    static Deque<Plant> plants = new LinkedList<>();

    static int[][] d = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        nutrient = new int[N][N];
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            nutrient[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            Arrays.fill(board[i], 5);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            plants.add(new Plant(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken())));
        }

        while (K-- > 0) {
            List<Plant>[] plantsList = spring();
            summer(plantsList[0]);
            fall(plantsList[1]);
            winter();
        }
        System.out.println(plants.size());
    }

    private static List<Plant>[] spring() {
        List<Plant> deadPlants = new ArrayList<>();
        List<Plant> growPlants = new ArrayList<>();

        for (int i = 0; i < plants.size();) {
            Plant current = plants.poll();

            if (board[current.x][current.y] < current.age) {
                deadPlants.add(current);
            } else {
                board[current.x][current.y] -= current.age++;
                plants.add(current);
                i++;

                if (current.age % 5 == 0) {
                    growPlants.add(current);
                }
            }
        }
        return new List[]{deadPlants, growPlants};
    }

    private static void summer(List<Plant> deadPlants) {
        for (Plant deadPlant : deadPlants) {
            board[deadPlant.x][deadPlant.y] += deadPlant.age / 2;
        }
    }

    private static void fall(List<Plant> growPlants) {
        for (int i = 0; i < growPlants.size(); i++) {
            Plant current = growPlants.get(i);

            for (int[] direction : d) {
                int nextX = current.x + direction[0];
                int nextY = current.y + direction[1];

                if (isValid(nextX, nextY)) {
                    plants.addFirst(new Plant(nextX, nextY, 1));
                }
            }
        }
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] += nutrient[i][j];
            }
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0<= nextX && nextX < N && 0 <= nextY && nextY < N;
    }

    static class Plant implements Comparable<Plant> {
        int x, y;
        int age;

        public Plant(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Plant o) {
            return this.age - o.age;
        }
    }
}
