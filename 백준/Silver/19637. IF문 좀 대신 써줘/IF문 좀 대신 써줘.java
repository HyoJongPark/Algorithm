import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[] nums;
    static Title[] titles;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        titles = new Title[N];
        nums = new int[M];
        for (int i = 0; i < N; i++) {
            titles[i] = new Title(br.readLine().split(" "));
        }
        for (int i = 0; i < M; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        for (int num : nums) {
            sb.append(binarySearch(num)).append("\n");
        }
        System.out.println(sb);
    }

    private static String binarySearch(int target) {
        int left = 0, right = N - 1;
        String result = null;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (titles[mid].power >= target) {
                right = mid - 1;
                result = titles[mid].title;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    static class Title {
        String title;
        int power;

        public Title(String[] args) {
            this.title = args[0];
            this.power = Integer.parseInt(args[1]);
        }
    }
}
