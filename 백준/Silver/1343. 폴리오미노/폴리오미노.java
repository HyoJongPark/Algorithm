import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '.') {
                sb.append(makeDomino(count)).append(".");
                count = 0;
                continue;
            }

            count++;
        }

        if (count != 0) {
            sb.append(makeDomino(count));
        }
        System.out.println(sb);
    }

    private static String makeDomino(int count) {
        if (count % 2 != 0) {
            System.out.println(-1);
            System.exit(0);
        }

        StringBuilder str = new StringBuilder();

        while (count > 0) {
            if (count >= 4) {
                count -= 4;
                str.append("AAAA");
            } else {
                count -= 2;
                str.append("BB");
            }
        }
        return str.toString();
    }
}
