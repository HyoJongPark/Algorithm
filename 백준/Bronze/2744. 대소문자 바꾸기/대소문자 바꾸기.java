import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] str = br.readLine().toCharArray();
        for (int i = 0; i < str.length; i++) {
            char currentChar = str[i];
            if (Character.isUpperCase(currentChar)) {
                str[i] = Character.toLowerCase(currentChar);
            } else {
                str[i] = Character.toUpperCase(currentChar);
            }
        }
        System.out.println(String.valueOf(str));
    }
}