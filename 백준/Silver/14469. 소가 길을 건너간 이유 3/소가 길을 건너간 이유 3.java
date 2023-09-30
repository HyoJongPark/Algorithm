import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static Cow[] cows;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cows = new Cow[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cows[i] = new Cow(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(cows);

        int currentTime = cows[0].arrivalTime;
        for (int i = 0; i < N; i++) {
            if (cows[i].arrivalTime <= currentTime) {
                currentTime += cows[i].passTime;
            } else {
                currentTime = cows[i].arrivalTime + cows[i].passTime;
            }
        }
        System.out.println(currentTime);
    }

    static class Cow implements Comparable<Cow> {
        int arrivalTime;
        int passTime;

        public Cow(int arrivalTime, int passTime) {
            this.arrivalTime = arrivalTime;
            this.passTime = passTime;
        }

        @Override
        public int compareTo(Cow o) {
            if (this.arrivalTime == o.arrivalTime) {
                return this.passTime - o.passTime;
            }
            return this.arrivalTime - o.arrivalTime;
        }
    }
}