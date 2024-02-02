import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    static int N;
    static char[][] relation;
    static int[][] distance;

    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        relation = new char[N][N];
        distance = new int[N][N];

        for (int i = 0; i < N; i++) {
            relation[i] = br.readLine().toCharArray();
            Arrays.fill(distance[i], INF);

            for (int j = 0; j < N; j++) {
                if (relation[i][j] == 'Y') distance[i][j] = 1;
            }
        }

        floyd();

        int result = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            int count = 0;
            for (int j = 0; j < N; j++) {
                if (distance[i][j] <= 2) {
                    count++;
                }
            }
            result = Math.max(result, count);
        }
        System.out.println(result);
    }

    private static void floyd() {
        for (int mid = 0; mid < N; mid++) {
            for (int st = 0; st < N; st++) {
                for (int en = 0; en < N; en++) {
                    if (st == en) continue;
                    if (relation[st][mid] == 'Y' && relation[mid][en] == 'Y' && distance[st][en] > distance[st][mid] + distance[mid][en]) {
                        distance[st][en] = distance[st][mid] + distance[mid][en];
                    }
                }
            }
        }
    }
}
