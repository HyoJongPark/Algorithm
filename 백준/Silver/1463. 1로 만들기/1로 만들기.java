import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int N;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        System.out.println(DFS(0, N));
    }

    private static int DFS(int level, int current) {
        if (current == 1) return level;
        else if (current < 1) return Integer.MAX_VALUE;
        else if (level > min) return Integer.MAX_VALUE;

        int tmp;
        if (current % 2 == 0) {
            tmp =  Math.min(
                    DFS(level + 1, current / 2),
                    DFS(level + 1, current - 1)
            );

            min = Math.min(tmp, min);
        }

        if (current % 3 == 0) {
            tmp =  Math.min(
                    DFS(level + 1, current / 3),
                    DFS(level + 1, current - 1)
            );

            min = Math.min(tmp, min);
        }

        if (current % 3 != 0 && current % 2 != 0) {
            min = Math.min(
                    DFS(level + 1, current - 1),
                    min
            );
        }

        return min;
    }
}