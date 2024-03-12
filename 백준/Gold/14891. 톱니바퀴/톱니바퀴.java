import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static char[][] gear = new char[4][8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            gear[i] = br.readLine().toCharArray();
        }

        int turn = Integer.parseInt(br.readLine());
        while (turn-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int numberOfGear = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            //turn
            turnAllGear(numberOfGear, direction);
        }

        System.out.println(calculateScore());
    }

    private static int calculateScore() {
        int socre = 0;
        for (int numberOfGear = 0; numberOfGear < 4; numberOfGear++) {
            if (gear[numberOfGear][0] == '1') {
                socre += (int) Math.pow(2, numberOfGear);
            }
        }
        return socre;
    }

    private static void turnAllGear(int numberOfGear, int direction) {
        char[][] newGear = new char[4][8];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 8; j++) {
                newGear[i][j] = gear[i][j];
            }
        }
        turnGear(numberOfGear, direction, newGear);

        //check left
        int nextDirection = direction == 1 ? -1 : 1;
        for (int leftIdx = numberOfGear - 1; leftIdx >= 0; leftIdx--) {
            if (gear[leftIdx][2] == gear[leftIdx + 1][6]) break;

            turnGear(leftIdx, nextDirection, newGear);
            nextDirection = nextDirection == 1 ? -1 : 1;
        }

        //check right
        nextDirection = direction == 1 ? -1 : 1;
        for (int rightIdx = numberOfGear + 1; rightIdx < 4; rightIdx++) {
            if (gear[rightIdx][6] == gear[rightIdx - 1][2]) break;

            turnGear(rightIdx, nextDirection, newGear);
            nextDirection = nextDirection == 1 ? -1 : 1;
        }
        gear = newGear;
    }

    private static void turnGear(int numberOfGear, int direction, char[][] newGear) {
        char nextVal = gear[numberOfGear][0];
        int nextPos = (8 + direction) % 8;

        while (nextPos != 0) {
            char tmp = gear[numberOfGear][nextPos];
            newGear[numberOfGear][nextPos] = nextVal;
            nextVal = tmp;

            nextPos = (nextPos + direction) % 8;
        }
        newGear[numberOfGear][0] = nextVal;
    }
}
