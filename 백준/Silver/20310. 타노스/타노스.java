import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<Character> characters = new ArrayList<>();
        char[] s = br.readLine().toCharArray();

        int numberOfZero = 0, numberOfOne = 0;
        for (char c : s) {
            characters.add(c);
            if (c == '0') {
                numberOfZero += 1;
            } else {
                numberOfOne += 1;
            }
        }

        int cnt = 0;
        int i = 0;
        while (cnt != numberOfOne / 2) {
            if (characters.get(i) == '1') {
                characters.remove(i);
                cnt++;
                continue;
            }
            i++;
        }

        cnt = 0;
        i = characters.size() - 1;
        while (cnt != numberOfZero / 2) {
            if (characters.get(i) == '0') {
                characters.remove(i);
                cnt++;
            }
            i--;
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : characters) {
            sb.append(c);
        }
        System.out.println(sb);
    }
}