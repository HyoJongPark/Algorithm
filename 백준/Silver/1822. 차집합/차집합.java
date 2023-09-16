import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int A, B;
    static int[] arr1, arr2;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        arr1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        arr2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(arr2);
        for (int target : arr1) {
            if (binarySearch(target)) {
                result.add(target);
            }
        }

        if (result.isEmpty()) {
            System.out.println(0);
        } else {
            result.sort(Integer::compare);
            StringBuilder sb = new StringBuilder();

            sb.append(result.size()).append("\n");
            result.forEach((num) -> sb.append(num).append(" "));
            System.out.println(sb);
        }
    }

    private static boolean binarySearch(int target) {
        int left = 0, right = arr2.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;

            if (arr2[mid] > target) {
                right = mid - 1;
            } else if (arr2[mid] < target) {
                left = mid + 1;
            } else {
                return false;
            }
        }

        return true;
    }
}