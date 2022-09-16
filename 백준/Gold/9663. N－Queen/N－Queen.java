import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int N, count = 0;
    static int[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N];
        DFS(0);
        System.out.println(count);
    }

    private static void DFS(int level) {
        if (level == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            board[level] = i;
            if (isValid(level)) {
                DFS(level + 1);
            }
        }
    }

    private static boolean isValid(int level) {
        for (int i = 0; i < level; i++) {
            if (board[i] == board[level] || level - i == Math.abs(board[level] - board[i])) {
                return false;
            }
        }
        return true;
    }
}