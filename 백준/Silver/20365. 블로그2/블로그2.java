import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        String input = br.readLine();

        int rCount = 0, bCount = 0;
        char before = '\0';
        for (char current : input.toCharArray()) {
            if (before != current) {
                if (current == 'B') bCount++;
                else rCount++;
            }
            before = current;
        }
        System.out.println(Math.min(bCount, rCount) + 1);
    }
}
