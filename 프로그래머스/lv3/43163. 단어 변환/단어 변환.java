import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(String begin, String target, String[] words) {
        return BFS(begin, target, words);
    }

    private int BFS(String begin, String target, String[] words) {
        Queue<Word> q = new LinkedList<>();
        HashSet<String> check = new HashSet<>();

        check.add(begin);
        q.offer(new Word(begin, 0));
        while (!q.isEmpty()) {
            Word current = q.poll();

            for (String next: words) {
                if (!check.contains(next) && canConvert(current.word, next)) {
                    if (next.equals(target)) return current.count + 1;

                    check.add(next);
                    q.offer(new Word(next, current.count + 1));
                }
            }
        }
        return 0;
    }

    private boolean canConvert(String current, String next) {
        int count = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != next.charAt(i)) {
                if (++count == 2) return false;
            }
        }
        return true;
    }

    static class Word {
        String word;
        int count;

        public Word(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}
