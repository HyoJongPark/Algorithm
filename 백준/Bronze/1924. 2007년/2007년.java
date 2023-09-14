import java.io.*;
import java.util.*;
public class Main {

    static int X, Y;
    static String[] days = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
    static int[] months = {0,31,28,31,30,31,30,31,31,30,31,30,31};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        int result = 0;
        for (int i = 0; i < X; i++) {
            result += months[i];
        }
        result += Y - 1;
        System.out.print(days[result % 7]);
    }
}
