import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Main {
    static int N, PIV = 1;
    static int[] tree, runners;
    static Integer[] sortedRunners;
    static Map<Integer, Integer> mapping = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        while (PIV < N) {
            PIV <<= 1;
        }
        tree = new int[2 * PIV];
        runners = new int[N];
        sortedRunners = new Integer[N];
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(br.readLine());
            runners[i] = value;
            sortedRunners[i] = value;
        }
        Arrays.sort(sortedRunners);
        mappingCount();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int index = mapping.get(runners[i]);
            update(PIV + index);
            sb.append(query(index, N)).append("\n");
        }
        System.out.println(sb);
    }

    private static int query(int start, int end) {
        int sum = 0;
        start += PIV;
        end += PIV - 1;
        while (start <= end) {
            if (start % 2 == 1) {
                sum += tree[start++];
            }
            if (end % 2 == 0) {
                sum += tree[end--];
            }

            start /= 2;
            end /= 2;
        }

        return sum;
    }

    private static void update(int index) {
        tree[index] = 1;
        index /= 2;
        while (index != 0) {
            tree[index] = tree[2 * index + 1] + tree[2 * index];
            index /= 2;
        }
    }

    private static void mappingCount() {
        for (int i = 0; i < N; i++) {
            mapping.put(sortedRunners[i], i);
        }
    }
}