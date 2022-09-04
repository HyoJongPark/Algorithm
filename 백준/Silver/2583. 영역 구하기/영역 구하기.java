import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

class Point {
    int start_x;
    int start_y;
    int end_x;
    int end_y;

    public Point(int start_x, int start_y, int end_x, int end_y) {
        this.start_x = start_x;
        this.start_y = start_y;
        this.end_x = end_x;
        this.end_y = end_y;
    }
}

class Main {
    static int N, M, K;
    static int[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = tmp[0];
        M = tmp[1];
        K = tmp[2];
        board = new int[N][M];
        for (int i = 0; i < K; i++) {
            tmp =  Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            createRectangle(new Point(tmp[1], tmp[0], tmp[3], tmp[2]));
        }

        StringBuilder sb = new StringBuilder();
        ArrayList<Integer> answers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 0) {
                    board[i][j] = 1;
                    answers.add(DFS(i, j));
                }
            }
        }
        answers.sort(Integer::compareTo);
        sb.append(answers.size()).append("\n");
        for (Integer answer : answers) sb.append(answer).append(" ");
        System.out.println(sb);
    }

    private static int DFS(int x, int y) {
        int answer = 1;

        for (int i = 0; i < 4; i++) {
            int next_x = x + dx[i];
            int next_y = y + dy[i];
            if (isValid(next_x, next_y)) {
                board[next_x][next_y] = 1;
                answer += DFS(next_x, next_y);
            }
        }
        return answer;
    }

    private static boolean isValid(int next_x, int next_y) {
        return 0 <= next_x && next_x < N && 0 <= next_y && next_y < M
                && board[next_x][next_y] == 0;
    }

    private static void createRectangle(Point point) {
        for (int i = point.start_x; i < point.end_x; i++) {
            for (int j = point.start_y; j < point.end_y; j++) {
                board[i][j] = 1;
            }
        }
    }
}