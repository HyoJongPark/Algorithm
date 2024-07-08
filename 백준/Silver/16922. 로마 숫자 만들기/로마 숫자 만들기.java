import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

class Main {

    static final int[] WORD = {1, 5, 10, 50};

    static int N;
    static boolean[][] check;
    static HashSet<Integer> number = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        check = new boolean[N][20 * 50 + 1];

        makeNumber(0, 0);
        System.out.println(number.size());
    }

    private static void makeNumber(int depth, int current) {
        if (depth == N) {
            number.add(current);
            return;
        }

        for (int i = 0; i < WORD.length; i++) {
            int next = current + WORD[i];
            if (check[depth][next]) {
                continue;
            }

            check[depth][next] = true;
            makeNumber(depth + 1, next);
        }
    }
}
