import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

class Main {

    static final List<Character> DUCK_SOUND = List.of('q', 'u', 'a', 'c', 'k');
    static char[] sound;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sound = br.readLine().toCharArray();

        System.out.println(checkSound());
    }

    private static int checkSound() {
        if (sound.length % 5 != 0 || sound[0] != 'q' || sound[sound.length - 1] != 'k') {
            return -1;
        }
        HashMap<Character, Integer> soundChecker = new HashMap<>();

        int duckCount = 0, result = Integer.MIN_VALUE;
        for (char current : sound) {
            if (current == 'q') {
                soundChecker.put('q', soundChecker.getOrDefault('q', 0) + 1);
                duckCount++;
                continue;
            }

            int beforeIdx = DUCK_SOUND.indexOf(current) - 1;
            char before = DUCK_SOUND.get(beforeIdx);
            int beforeSoundCount = soundChecker.getOrDefault(before, 0);

            if (beforeSoundCount == 0) {
                return -1;
            }

            soundChecker.put(before, --beforeSoundCount);
            soundChecker.put(current, soundChecker.getOrDefault(current, 0) + 1);
            result = Math.max(result, duckCount);

            if (current == 'k') {
                duckCount--;
            }
        }

        for (int i = 0; i < DUCK_SOUND.size() - 1; i++) {
            if (soundChecker.getOrDefault(DUCK_SOUND.get(i), 0) != 0) {
                return -1;
            }
        }
        return result;
    }
}
