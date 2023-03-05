import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    //R  L B T RT LT RB LB
    static int[] dx = {1, -1, 0, 0, 1, -1, 1, -1};
    static int[] dy = {0, 0, -1, 1, 1, 1, -1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Point king = new Point(st.nextToken());
        Point stone = new Point(st.nextToken());

        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            int index = findDirection(br.readLine());
            int nextX = king.x + dx[index], nextY = king.y + dy[index];

            if (isValid(nextX, nextY)) {
                if (nextX == stone.x && nextY == stone.y) {
                    if (isValid(stone.x + dx[index], stone.y + dy[index])) {
                        stone.x += dx[index];
                        stone.y += dy[index];
                    } else continue;
                }
                king.x = nextX;
                king.y = nextY;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append((char) (king.x + 'A' - 1)).append(king.y).append("\n");
        sb.append((char) (stone.x + 'A' - 1)).append(stone.y);
        System.out.println(sb);
    }

    private static int findDirection(String command) {
        switch (command) {
            case "R":
                return 0;
            case "L":
                return 1;
            case "B":
                return 2;
            case "T":
                return 3;
            case "RT":
                return 4;
            case "LT":
                return 5;
            case "RB":
                return 6;
            case "LB":
                return 7;
        }
        return -1;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 < nextX && nextX <= 8 && 0 < nextY && nextY <= 8;
    }

    static class Point {
        int x;
        int y;

        public Point(String input) {
            this.x = input.charAt(0) - 'A' + 1;
            this.y = input.charAt(1) - '0';
        }
    }
}