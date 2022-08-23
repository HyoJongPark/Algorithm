import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

class Main {
    static int N;
    static int[] numbers;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int []signs = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        DFS(1, numbers[0], signs);
        StringBuilder sb = new StringBuilder();
        sb.append(max).append("\n").append(min);
        System.out.println(sb);
    }

    private static void DFS(int level, int value, int[] signs) {
        if (level == N) {
            max = Math.max(max, value);
            min = Math.min(min, value);
            return;
        }

        if (signs[0] > 0) {
            int[] newSigns = signs.clone();
            newSigns[0] -= 1;
            DFS(level + 1, value + numbers[level], newSigns);
        }
        if (signs[1] > 0) {
            int[] newSigns = signs.clone();
            newSigns[1] -= 1;
            DFS(level + 1, value - numbers[level], newSigns);
        }
        if (signs[2] > 0) {
            int[] newSigns = signs.clone();
            newSigns[2] -= 1;
            DFS(level + 1, value * numbers[level], newSigns);
        }
        if (signs[3] > 0) {
            int[] newSigns = signs.clone();
            newSigns[3] -= 1;
            DFS(level + 1, value / numbers[level], newSigns);
        }
    }
}