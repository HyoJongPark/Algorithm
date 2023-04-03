import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static final String STAIR = "Stair";
    private static final int ST_HEIGHT = 17;
    private static final int ES_HEIGHT = 21;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int result = 0;
        for (int i = 0; i < 4; i++) {
            String[] input = br.readLine().split(" ");

            if (input[0].equals(STAIR)) {
                result += ST_HEIGHT * Integer.parseInt(input[1]);
            } else {
                result += ES_HEIGHT * Integer.parseInt(input[1]);
            }
        }

        System.out.println(result);
    }
}