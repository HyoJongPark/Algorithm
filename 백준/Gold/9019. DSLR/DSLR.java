import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


class Main {
    static int origin;
    static int target;
    static char[] register = {'D', 'S', 'L', 'R'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            origin = Integer.parseInt(st.nextToken());
            target = Integer.parseInt(st.nextToken());
            sb.append(BFS()).append("\n");
        }

        System.out.println(sb);
    }

    private static String BFS() {
        boolean[] check = new boolean[10000];
        Queue<DSLR> q = new LinkedList<>();
        q.offer(new DSLR(origin, ""));
        check[origin] = true;

        while (!q.isEmpty()) {
            DSLR dslr = q.poll();

            for (int i = 0; i < 4; i++) {
                DSLR next = useRegister(dslr, register[i]);

                if (next.current == target) {
                    return next.command;
                } else if (!check[next.current]) {
                    check[next.current] = true;
                    q.offer(next);
                }
            }
        }
        return "ERROR";
    }

    private static DSLR useRegister(DSLR dslr, char register) {
        int next;

        if (register == 'D') {
            next = (dslr.current * 2 > 9999 ? dslr.current * 2 % 10000 : dslr.current * 2);
        } else if (register == 'S') {
            next = (dslr.current == 0 ? 9999 : dslr.current - 1);
        } else if (register == 'L') {
            next = (dslr.current / 1000) + (dslr.current % 1000 * 10);
        } else {
            next = (dslr.current % 10 * 1000) + (dslr.current / 10);
        }
        return new DSLR(next, dslr.command + register);
    }

    static class DSLR {
        int current;
        String command;

        public DSLR(int current, String command) {
            this.current = current;
            this.command = command;
        }
    }
}
