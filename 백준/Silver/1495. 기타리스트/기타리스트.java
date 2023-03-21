import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    static int N, S, M;
    static int[] d, sound;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        d = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            d[i] = Integer.parseInt(st.nextToken());
        }

        findMaxSound();
        for (int i = M; i >= 0; i--) {
            if (sound[i] == N) {
                System.out.println(i);
                return;
            }
        }
        System.out.println(-1);
    }

    private static void findMaxSound() {
        ArrayList<Integer> list = new ArrayList<>();
        sound = new int[M + 1];
        Arrays.fill(sound, -1);

        sound[S] = 0;
        for (int i = 1; i <= N; i++) {
            list.clear();
            for (int j = 0; j <= M; j++) {
                if (sound[j] == i - 1) {
                    if (0 <= j - d[i] && j - d[i] <= M) {
                        list.add(j - d[i]);
                    }
                    if (0 <= j + d[i] && j + d[i] <= M) {
                        list.add(j + d[i]);
                    }
                }
            }
            for (int v : list) {
                sound[v] = i;
            }
        }
    }
}