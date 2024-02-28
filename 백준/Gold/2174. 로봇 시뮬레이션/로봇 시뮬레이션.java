import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
    static int A, B, N, M;
    static int[][] board;
    static Robot[] robots;
    static int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; //N ->, E 아래, S <-, W 위
    static HashMap<Character, Integer> directionToInt = new HashMap<>();

    public static void main(String[] args) throws IOException {
        directionToInt.put('N', 0);
        directionToInt.put('E', 1);
        directionToInt.put('S', 2);
        directionToInt.put('W', 3);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        robots = new Robot[N + 1];
        board = new int[A + 1][B + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().toCharArray()[0];

            robots[i] = new Robot(directionToInt.get(direction), x, y);
            board[x][y] = i;
        }


        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int robotNo = Integer.parseInt(st.nextToken());
            char command = st.nextToken().toCharArray()[0];
            int routine = Integer.parseInt(st.nextToken());

            runCommand(robotNo, command, routine);
        }

        System.out.println("OK");
    }

    //SUCCESS: 0, CRASH_ROBOT: 1, CRASH_WALL: 2
    private static void runCommand(int robotNo, char command, int routine) {
        if (command == 'L') {
            turnRobot(robotNo, -1, routine);
        } else if (command == 'R') {
            turnRobot(robotNo, 1, routine);
        } else if (command == 'F') {
            moveFront(robotNo, routine);
        }
    }

    private static void turnRobot(int robotNo, int turnDirection, int routine) {
        Robot robot = robots[robotNo];

        int nextDirection = robot.direction + (turnDirection * routine);
        while(nextDirection < 0) nextDirection += 4;

        nextDirection = nextDirection % 4;
        robot.direction = nextDirection;
    }

    private static void moveFront(int robotNo, int routine) {
        Robot robot = robots[robotNo];
        while (routine-- > 0) {
            int nextX = robot.x + d[robot.direction][0];
            int nextY = robot.y + d[robot.direction][1];

            if (!isValid(nextX, nextY)) {
                System.out.printf("Robot %d crashes into the wall\n", robotNo);
                System.exit(0);
            }
            if (board[nextX][nextY] != 0) {
                System.out.printf("Robot %d crashes into robot %d\n", robotNo, board[nextX][nextY]);
                System.exit(0);
            }

            board[robot.x][robot.y] = 0;
            board[nextX][nextY] = robotNo;
            robot.x = nextX;
            robot.y = nextY;
        }
    }

    private static boolean isValid(int nextX, int nextY) {
        return 1 <= nextX && nextX <= A && 1 <= nextY && nextY <= B;
    }

    static class Robot {
        int direction;
        int x, y;

        public Robot(int direction, int x, int y) {
            this.direction = direction;
            this.x = x;
            this.y = y;
        }
    }
}
