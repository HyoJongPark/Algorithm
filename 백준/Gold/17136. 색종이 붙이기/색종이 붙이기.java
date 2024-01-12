import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

class Main {
    static int N = 10, result = Integer.MAX_VALUE;
    static int[][] board;
    static boolean[][] check;
    static HashMap<Integer, Integer> paper = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new int[N][N];
        check = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        for (int i = 1; i <= 5; i++) {
            paper.put(i, 5);
        }

        DFS(0, 0, 0);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void DFS(int x, int y, int count) {
        if (count >= result) return;
        if (x == 10) {
            result = count;
            return;
        } else if (y == 10) {
            DFS(x + 1, 0, count);
            return;
        }

        if (board[x][y] == 1 && !check[x][y]) {
            for (int i = 5; i >= 1; i--) {
                if (paper.get(i) > 0 && canAttach(x, y, i)) {
                    paper.put(i, paper.get(i) - 1);
                    attachOrDetachPaper(x, y, i, true);
                    DFS(x, y + 1, count + 1);
                    paper.put(i, paper.get(i) + 1);
                    attachOrDetachPaper(x, y, i, false);
                }
            }
        } else {
            DFS(x, y + 1, count);
        }
    }

    private static void attachOrDetachPaper(int x, int y, int size, boolean status) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (isValid(i, j)) {
                    check[i][j] = status;
                }
            }
        }
    }

    private static boolean canAttach(int x, int y, int size) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (!isValid(i, j) || (board[i][j] == 0 || check[i][j])) return false;
            }
        }
        return true;
    }

    private static boolean isValid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
