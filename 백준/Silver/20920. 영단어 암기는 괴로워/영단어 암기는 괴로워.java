import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        HashMap<String, Word> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String current = br.readLine();
            if (current.length() < M) continue;

            Word value = map.get(current);
            if (value == null) {
                map.put(current, new Word(current, 1));
            } else {
                map.put(current, new Word(current, value.count + 1));
            }
        }

        StringBuilder sb = new StringBuilder();
        map.values().stream()
                .sorted()
                .forEach(w -> sb.append(w.word).append("\n"));
        System.out.println(sb);
    }

    static class Word implements Comparable<Word> {
        String word;
        int count;

        public Word(String word, int count) {
            this.word = word;
            this.count = count;
        }


        @Override
        public int compareTo(Word o) {
            if (o.count == this.count) {
                if (o.word.length() == this.word.length()) {
                    return this.word.compareTo(o.word);
                }
                return o.word.length() - this.word.length();
            }
            return o.count - this.count;
        }
    }
}