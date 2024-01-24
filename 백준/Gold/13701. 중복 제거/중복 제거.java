import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        BitSet set = new BitSet(5_000_001);
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreTokens()) {
            int next = Integer.parseInt(st.nextToken());

            if (!set.get(next)) {
                set.set(next);
                sb.append(next).append(" ");
            }
        }
        System.out.println(sb);
    }
}
