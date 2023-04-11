import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    static int N, M;
    static char[][] board;
    static int[][] area;
    static boolean[] check;
    static HashMap<Integer, Integer> areas = new HashMap<>();
    static int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //initialize
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        //make area
        int areaNo = 1;
        area = new int[N][M];
        Queue<int[]> walls = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == '0' && area[i][j] == 0) {
                    makeArea(i, j, areaNo);
                    areaNo++;
                } else {
                    walls.offer(new int[]{i, j});
                }
            }
        }

        //find result
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int result = 0;
                if (board[i][j] == '1') {
                    check = new boolean[areaNo];
                    result += findArea(i, j) + 1;
                }
                sb.append(result % 10);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void makeArea(int x, int y, int areaNo) {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{x, y});
        area[x][y] = areaNo;
        int areaSize = 1;
        while (!q.isEmpty()) {
            int[] current = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current[0] + d[i][0];
                int nextY = current[1] + d[i][1];

                if (isValid(nextX, nextY) && area[nextX][nextY] == 0 && board[nextX][nextY] == '0') {
                    q.offer(new int[]{nextX, nextY});
                    area[nextX][nextY] = areaNo;
                    areaSize++;
                }
            }
        }

        areas.put(areaNo, areaSize);
    }

    private static int findArea(int x, int y) {
        int totalSize = 0;
        for (int i = 0; i < 4; i++) {
            int nextX = x + d[i][0];
            int nextY = y + d[i][1];

            if (isValid(nextX, nextY) && area[nextX][nextY] != 0 && !check[area[nextX][nextY]]) {
                check[area[nextX][nextY]] = true;
                totalSize += areas.get(area[nextX][nextY]);
            }
        }

        return totalSize;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < M;
    }
}