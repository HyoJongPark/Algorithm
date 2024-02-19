import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, result = 0;
    static Egg[] eggs;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        eggs = new Egg[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        breakEgg(0, 0);

        System.out.println(result);
    }

    private static void breakEgg(int eggIndex, int count) {
        if (eggIndex == N) return;

        Egg current = eggs[eggIndex];
        if (current.durability <= 0) {
            breakEgg(eggIndex + 1, count);
            return;
        }

        for (int nextIndex = 0; nextIndex < N; nextIndex++) {
            Egg next = eggs[nextIndex];
            if (eggIndex == nextIndex || next.durability <= 0) continue;

            int currentCount = count;

            current.durability -= next.weight;
            next.durability -= current.weight;
            if (current.durability <= 0) currentCount++;
            if (next.durability <= 0) currentCount++;

            breakEgg(eggIndex + 1, currentCount);

            current.durability += next.weight;
            next.durability += current.weight;

            result = Math.max(result, currentCount);
        }
    }

    static class Egg {
        int durability, weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }
}
