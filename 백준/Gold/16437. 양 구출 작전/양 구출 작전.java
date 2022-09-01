import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[] numberOfAnimal;
    static boolean[] animalType;
    static List<Integer>[] list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        numberOfAnimal = new int[N + 1];
        animalType = new boolean[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) list[i] = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            if (st.nextToken().equals("S")) animalType[i] = true;
            numberOfAnimal[i] = Integer.parseInt(st.nextToken());
            list[Integer.parseInt(st.nextToken())].add(i);
        }

        System.out.println(DFS(1));
    }

    private static long DFS(int level) {
        long cnt = 0;
        for (int next : list[level]) {
            cnt += DFS(next);
        }

        if (level == 1) {
            return cnt;
        } else {
            if (animalType[level]) {
                cnt += numberOfAnimal[level];
            } else {
                cnt -= numberOfAnimal[level];
                if (cnt < 0) cnt = 0;
            }
        }
        return cnt;
    }
}