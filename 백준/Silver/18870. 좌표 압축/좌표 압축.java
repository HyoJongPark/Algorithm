import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[] point, sorted;
    static HashMap<Integer, Integer> rank = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        point = new int[N];
        sorted = new int[N];
        for (int i = 0; i < N; i++) {
            point[i] = sorted[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sorted);

        initializeRank();

        StringBuilder sb = new StringBuilder();
        for (int target : point) {
            sb.append(rank.get(target)).append(" ");
        }
        System.out.println(sb);
    }

    private static void initializeRank() {
        int currentRank = 0;

        for (int num : sorted) {
            if (!rank.containsKey(num)) {
                rank.put(num, currentRank++);
            }
        }
    }
}