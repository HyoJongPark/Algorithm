import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

class Main {
    static String S, T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        if (bfs()) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static boolean bfs() {
        Queue<String> q = new LinkedList<>();
        HashSet<String> check = new HashSet<>();

        q.offer(T);
        check.add(T);
        while (!q.isEmpty()) {
            String current = q.poll();
            if (current.length() == S.length()) continue;

            if (current.startsWith("B")) {
                String next = makeNextString(current);
                
                if (checkAndPush(next, check, q)) return true;
            }
            if (current.endsWith("A")) {
                String next = current.substring(0, current.length() - 1);

                if (checkAndPush(next, check, q)) return true;
            }
        }

        return false;
    }

    private static boolean checkAndPush(String next, HashSet<String> check, Queue<String> q) {
        if (next.equals(S)) return true;

        if (!check.contains(next)) {
            check.add(next);
            q.offer(next);
        }
        return false;
    }

    private static String makeNextString(String current) {
        char[] d = current.substring(1).toCharArray();
        char[] next = new char[d.length];
        for (int i = 0; i < d.length; i++) {
            next[i] = d[d.length - i - 1];
        }

        return new String(next);
    }
}
