import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static HashMap<Character, Integer>[] count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        count = new HashMap[M + 1];
        for (int i = 0; i < M; i++) {
            count[i] = new HashMap<>();
        }

        for (int i = 0; i < N; i++) {
            String current = br.readLine();

            for (int j = 0; j < M; j++) {
                char c = current.charAt(j);

                count[j].put(c, count[j].getOrDefault(c, 0) + 1);
            }
        }

        int result = 0;
        StringBuilder sb = new StringBuilder();
        char[] dna = {'A', 'C', 'G', 'T'};
        for (int i = 0; i < M; i++) {
            int max = 0, currentSum = 0;
            char currentChar = 'A';

            for (char c : dna) {
                int current = count[i].getOrDefault(c, 0);

                if (current != 0 && current > max) {
                    currentSum += max;
                    max = current;
                    currentChar = c;
                } else if (current != 0) {
                    currentSum += current;
                }
            }
            result += currentSum;
            sb.append(currentChar);
        }
        sb.append("\n").append(result);
        System.out.println(sb);
    }
}
