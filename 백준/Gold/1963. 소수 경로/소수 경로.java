import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    static int T;
    static boolean[] isNotPrime = new boolean[10_000];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        isNotPrime[0] = true;
        isNotPrime[1] = true;
        for (int i = 2; i < isNotPrime.length; i++) {
            if (isNotPrime[i]) continue;

            for (int j = 2; (i * j) < isNotPrime.length; j++) {
                isNotPrime[i * j] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String[] input = br.readLine().split(" ");

            if (input[0].equals(input[1])) {
                sb.append("0\n");
                continue;
            }

            int result = BFS(input[0], input[1]);
            if (result == -1) {
                sb.append("Impossible\n");
            } else {
                sb.append(result).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static int BFS(String start, String target) {
        Queue<Number> q = new LinkedList<>();
        boolean[] check = new boolean[10_000];

        check[Integer.parseInt(start)] = true;
        q.offer(new Number(start, 0));
        while (!q.isEmpty()) {
            Number current = q.poll();

            for (int i = 0; i < 4; i++) {
                for (int nextChar = 0; nextChar <= 9; nextChar++) {
                    char[] next = current.number.toCharArray();
                    next[i] = (char)(nextChar + '0');

                    String nextStr = String.valueOf(next);
                    int nextInt = Integer.parseInt(nextStr);

                    if (nextInt == Integer.parseInt(target)) {
                        return current.count + 1;
                    }
                    if (isValid(nextInt) && !check[nextInt]) {
                        check[nextInt] = true;
                        q.offer(new Number(nextStr, current.count + 1));
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isValid(int num) {
        return 1000 <= num && num < 10_000 && !isNotPrime[num];
    }

    static class Number {
        String number;
        int count;

        public Number(String number, int count) {
            this.number = number;
            this.count = count;
        }
    }
}
