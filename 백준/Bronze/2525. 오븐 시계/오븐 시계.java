import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int hour = Integer.parseInt(st.nextToken()), minute = Integer.parseInt(st.nextToken());
        minute += Integer.parseInt(br.readLine());

        hour += minute / 60;
        minute %= 60;
        hour %= 24;

        System.out.printf("%d %d", hour, minute);
    }
}