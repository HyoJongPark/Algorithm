import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

class Main {
    static int N, M;
    static List<Integer> cranes = new ArrayList<>();
    static List<Integer> boxes = new ArrayList<>();
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cranes = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        M = Integer.parseInt(br.readLine());
        boxes = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        check = new boolean[M];

        cranes.sort(Collections.reverseOrder());
        boxes.sort(Collections.reverseOrder());

        int time = 0, count = 0;
        if (boxes.get(0) > cranes.get(0)) {
            System.out.println(-1);
            System.exit(0);
        }
        while (count != M) {
            int boxIdx = 0;

            for (int craneIdx = 0; craneIdx < N; ) {
                if (boxIdx >= M) break;

                if (!check[boxIdx] && boxes.get(boxIdx) <= cranes.get(craneIdx)) {
                    check[boxIdx] = true;
                    count++;
                    craneIdx++;
                }
                boxIdx++;
            }
            time++;
        }
        System.out.println(time);
    }
}
