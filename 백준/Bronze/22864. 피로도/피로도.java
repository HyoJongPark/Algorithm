import java.io.*;
import java.util.*;

public class Main {

    static int A, B, C, M;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int count = 0, p = 0, answer = 0;
        while(count != 24) {
            if(p + A <= M) {
                answer += B;
                p += A;
            } else if (p - C < 0) {
                p = 0;
            } else {
                p -= C;
            }
            count++;
        }
        System.out.print(answer);
    }
}