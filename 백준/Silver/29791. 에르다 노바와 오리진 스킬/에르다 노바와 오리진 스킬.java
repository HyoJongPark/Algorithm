import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] nova, origin;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nova = new int[N];
        origin = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nova[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            origin[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nova);
        Arrays.sort(origin);

        int novaTime = calculateStunTime(nova, 100);
        int originTime = calculateStunTime(origin, 360);
        System.out.printf("%d %d", novaTime, originTime);
    }

    private static int calculateStunTime(int[] skillTime, int coolTime) {
        int count = 1;
        int skillUseTime = skillTime[0];

        for (int i = 1; i < skillTime.length; i++) {
            if (skillTime[i] < skillUseTime + coolTime) {
                continue;
            }

            skillUseTime = skillTime[i];
            count++;
        }
        return count;
    }
}
