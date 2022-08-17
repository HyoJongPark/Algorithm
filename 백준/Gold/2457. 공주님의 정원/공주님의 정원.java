import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class Day {
    int start;
    int end;

    public Day(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Main {

    static int N;
    static int start = 301;
    static int end = 1130;
    static ArrayList<Day> days = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            days.add(new Day(tmp[0] * 100 + tmp[1], tmp[2] * 100 + tmp[3]));
        }

        days.sort(new Comparator<Day>() {
            @Override
            public int compare(Day o1, Day o2) {
                if (o1.start == o2.start) return o2.end - o1.end;
                return o1.start - o2.start;
            }
        });

        int index = 0, max = 0, cnt = 0;
        boolean canSolve = true;
        //도달 못했을 경우는 어떻게할까?
        while (start <= end) {
            for (int i = index; i < N; i++) {
                if (days.get(i).start > start) {
                    index = i;
                    break;
                }
                max = Math.max(days.get(i).end, max);
            }
            if (start == max) {
                canSolve = false;
                break;
            }
            start = max;
            cnt++;
        }

        if (canSolve) {
            System.out.println(cnt);
        } else {
            System.out.println(0);
        }
    }
}