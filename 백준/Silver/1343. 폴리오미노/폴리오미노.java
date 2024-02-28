import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        String replaceToA = input.replaceAll("XXXX", "AAAA");
        String replaceToB = replaceToA.replaceAll("XX", "BB");

        if (replaceToB.contains("X")) {
            System.out.println(-1);
        } else {
            System.out.println(replaceToB);
        }
    }
}
