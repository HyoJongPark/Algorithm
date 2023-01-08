import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Map<Character, Integer> count = new TreeMap<>();
        for (int i = 0; i < N; i++) {
            char c = br.readLine().charAt(0);
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        String result = count.entrySet().stream()
                .filter(entry -> entry.getValue() >= 5)
                .map(entry -> String.valueOf(entry.getKey()))
                .collect(Collectors.joining());

        if (result.equals("")) {
            System.out.println("PREDAJA");
        } else {
            System.out.println(result);
        }
    }
}
