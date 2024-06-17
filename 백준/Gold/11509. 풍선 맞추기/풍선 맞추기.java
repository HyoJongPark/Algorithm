import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {

    static int N;
    static int[] balloon;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        balloon = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int count = 0;
        for (int i = 0; i < N; i++) {
            if (balloon[i] != -1) {
                shot(i, balloon[i]);
                count++;
            }
        }
        System.out.println(count);
    }

    private static void shot(int idx, int height) {
        for (; idx < N; idx++) {
            if (balloon[idx] == height) {
                balloon[idx] = -1;
                height--;
            }
        }
    }
}
