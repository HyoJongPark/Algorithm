import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N, M, lastMinus = -1;
    static int[] books;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        books = new int[N];
        for (int i = 0; i < N; i++) {
            books[i] = Integer.parseInt(st.nextToken());

            if (books[i] < 0) lastMinus++;
        }

        Arrays.sort(books);
        int distance = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i <= lastMinus; i += M) {
            distance += Math.abs(books[i]) * 2;
            max = Math.max(Math.abs(books[i]), max);
        }
        for (int i = N - 1; i > lastMinus; i -= M) {
            distance += books[i] * 2;
            max = Math.max(books[i], max);
        }
        System.out.println(distance - max);
    }
}
