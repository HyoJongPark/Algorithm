import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int N, red = 0, blue = 0, result = Integer.MAX_VALUE;
    static String str;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        str = br.readLine();

        for (int i = 0; i < N; i++) {
            if (str.charAt(i) == 'R') {
                red++;
            } else {
                blue++;
            }
        }
        calMoveCount('R');
        calMoveCount('B');
        System.out.println(result);
    }

    private static void calMoveCount(char target) {
        int count = moveRight(target);
        count = Math.max(count, moveLeft(target));

        if (target == 'R') {
            result = Math.min(red - count, result);
        } else {
            result = Math.min(blue - count, result);
        }
    }

    private static int moveLeft(char target) {
        int count = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (str.charAt(i) == target) count++;
            else {
                return count;
            }
        }
        return count;
    }

    private static int moveRight(char target) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (str.charAt(i) == target) count++;
            else {
                return count;
            }
        }
        return count;
    }
}
