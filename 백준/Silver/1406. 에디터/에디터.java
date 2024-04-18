import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<Character> originList = new LinkedList<>();
        char[] init = br.readLine().toCharArray();

        for (char c : init) {
            originList.add(c);
        }

        int M = Integer.parseInt(br.readLine());
        ListIterator<Character> iter = originList.listIterator();
        while (iter.hasNext()) {
            iter.next();
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char command = st.nextToken().charAt(0);

            if (command == 'L') {
                if (!iter.hasPrevious()) {
                    continue;
                }

                iter.previous();
            } else if (command == 'D') {
                if (!iter.hasNext()) {
                    continue;
                }

                iter.next();
            } else if (command == 'B') {
                if (!iter.hasPrevious()) {
                    continue;
                }

                iter.previous();
                iter.remove();
            } else {
                iter.add(st.nextToken().charAt(0));
            }
        }

        StringBuilder sb = new StringBuilder();
        originList.iterator().forEachRemaining(sb::append);
        System.out.println(sb);
    }
}
