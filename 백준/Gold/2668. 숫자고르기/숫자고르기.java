import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[] board;
    static boolean[] check;
    static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        board = new int[N + 1];

        for(int i = 1; i <= N; i++) {
            board[i] = Integer.parseInt(br.readLine());
        }

        check = new boolean[N + 1];
        for(int i = 1; i <= N; i++) {
            check[i] = true;
            DFS(i, i);
            check[i] = false;
        }
        result.sort(Integer::compareTo);

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append("\n");
        result.forEach((num) -> sb.append(num).append("\n"));
        System.out.println(sb);
    }

    public static void DFS(int current, int target) {
        if(board[current] == target) {
            result.add(target);
            return;
        }

        if(!check[board[current]]) {
            check[board[current]] = true;
            DFS(board[current], target);
            check[board[current]] = false;
        }
    }
}
