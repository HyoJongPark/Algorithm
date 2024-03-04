import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int N;
    static int[] books;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        books = new int[N];

        int maxIndex = 0;
        for (int i = 0; i < N; i++) {
            books[i] = Integer.parseInt(br.readLine());

            if (books[i] == N) {
                maxIndex = i;
            }
        }

        int target = N - 1, count = 1;
        for (int i = maxIndex - 1; i >= 0; i--) {
            if (books[i] == target) {
                target--;
                count++;
            }
        }
        System.out.println(N - count);
    }
}
