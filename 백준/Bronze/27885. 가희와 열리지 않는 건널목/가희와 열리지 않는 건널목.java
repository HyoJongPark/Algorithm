import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static List<Train> trains = new ArrayList<>();
    public static final int END_TIME_OF_DAY = 24 * 3600;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            String[] time = br.readLine().split(":");
            trains.add(new Train(time));
        }
        trains.sort(Train::compareTo);

        int currentTime = 0, result = 0;
        for (Train train : trains) {
            if (currentTime < train.enterTime) {
                result += train.enterTime - currentTime;
                currentTime = train.outTime;
            } else {
                currentTime = train.outTime;
            }
        }

        System.out.println(result + (END_TIME_OF_DAY - currentTime));
    }

    static class Train implements Comparable<Train> {
        int enterTime;
        int outTime;

        public Train(String[] enterTime) {
            this.enterTime = 3600 * Integer.parseInt(enterTime[0]) + 60 * Integer.parseInt(enterTime[1]) + Integer.parseInt(enterTime[2]);
            this.outTime = this.enterTime + 40;
        }

        @Override
        public int compareTo(Train o) {
            return this.enterTime - o.enterTime;
        }
    }
}