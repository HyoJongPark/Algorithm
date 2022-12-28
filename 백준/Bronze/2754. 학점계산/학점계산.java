import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] grade = br.readLine().toCharArray();
        double score = getDefaultScore(grade[0]) + getPlusOrMinus(grade[1]);
        System.out.println(score);
    }

    private static double getDefaultScore(char c) {
        if (c == 'A') {
            return 4.0;
        } else if (c == 'B') {
            return 3.0;
        } else if (c == 'C') {
            return 2.0;
        } else if (c == 'D') {
            return 1.0;
        } else {
            System.out.println(0.0);
            System.exit(0);
            return 0.0;
        }
    }

    private static double getPlusOrMinus(char c) {
        if (c == '+') {
            return 0.3;
        } else if (c == '-') {
            return -0.3;
        }
        return 0;
    }
}