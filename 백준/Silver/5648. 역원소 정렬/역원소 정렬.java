import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        ArrayList<Long> list = new ArrayList<>();
        StringBuilder sb;
        String input;
        while (list.size() != N) {
            while (st.hasMoreTokens()){
                sb = new StringBuilder(st.nextToken());
                list.add(Long.parseLong(sb.reverse().toString()));
            }

            if ((input = br.readLine()) != null) {
                st = new StringTokenizer(input);
            }
        }
        list.sort(Comparator.naturalOrder());
        sb = new StringBuilder();
        for (Long num : list) {
            sb.append(num).append("\n");
        }
        System.out.println(sb);
    }
}