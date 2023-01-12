import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.max;

public class Main {
    static int N, PIV = 1;
    static int[] arr, tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        while (PIV < 1000000) {
            PIV <<= 1;
        }
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        tree = new int[2 * PIV];

        for (int i = 0; i < N; i++) {
            update(arr[i], query(arr[i]));
        }
        System.out.println(tree[1]);
    }

    private static int query(int value) {
        int start = PIV, end = PIV + value - 2, cnt = 0;
        while (start <= end) {
            if (start % 2 == 1) {
                cnt = max(cnt, tree[start++]);
            }
            if (end % 2 == 0) {
                cnt = max(cnt, tree[end--]);
            }
            start /= 2;
            end /= 2;
        }

        return cnt;
    }

    private static void update(int i, int value) {
        int index = PIV + i - 1;
        tree[index] = max(tree[index], value + 1);
        index /= 2;
        while (index != 0) {
            tree[index] = max(tree[index * 2], tree[index * 2 + 1]);
            index /= 2;
        }
    }
}
