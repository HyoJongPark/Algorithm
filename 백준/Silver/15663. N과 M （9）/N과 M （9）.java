import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] numbers, result;
    static boolean[] check;
    static LinkedHashSet<String> answer = new LinkedHashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        result = new int[M];
        check = new boolean[N];
        Arrays.sort(numbers);

        DFS(0);
        answer.forEach(System.out::println);
    }

    static void DFS(int level) {
        StringBuilder sb = new StringBuilder();
        if (level == M) {
            for (int num : result){
                sb.append(num).append(' ');
            }
            answer.add(sb.toString());
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!check[i]) {
                check[i] = true;
                result[level] = numbers[i];
                DFS(level + 1);
                check[i] = false;
            }
        }
    }
}