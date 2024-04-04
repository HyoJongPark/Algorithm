import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    static int N;
    static Bar[] bar;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        bar = new Bar[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            bar[i] = new Bar(L, H);
        }
        Arrays.sort(bar, (b1, b2) -> b1.l - b2.l);

        System.out.println(calculateResult());
    }

    private static int calculateResult() {
        int result = 0;
        for (int i = 0; i < N;) {
            int j = i + 1, maxBarPos = j;

            while(j < N && bar[i].h > bar[j].h) {
                if (bar[maxBarPos].h < bar[j++].h) {
                    maxBarPos = j - 1;
                }
            }

            if (j >= N) {
                result += bar[i].h;
                if (maxBarPos < N) {
                    result += bar[maxBarPos].h * (bar[maxBarPos].l - bar[i].l - 1);
                }

                i = maxBarPos;
            } else {
                result += bar[i].h * (bar[j].l - bar[i].l);
                i = j;
            }
        }
        return result;
    }

    static class Bar {
        int l, h;

        public Bar(int l, int h) {
            this.l = l;
            this.h = h;
        }
    }
}
