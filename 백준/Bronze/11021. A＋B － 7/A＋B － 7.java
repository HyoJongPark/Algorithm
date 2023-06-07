import java.util.*;
import java.io.*;

class Main {
    
    static int T;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            sb.append(String.format("Case #%d: %d\n", i, Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken())));
        }
        System.out.print(sb);
    }
}