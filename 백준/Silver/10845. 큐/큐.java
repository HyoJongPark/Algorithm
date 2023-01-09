import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, first = 0, last = 0;
    static int[] queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        queue = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            switch (command) {
                case "push":
                    push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(pop()).append("\n");
                    break;
                case "size":
                    sb.append(last - first).append("\n");
                    break;
                case "empty":
                    sb.append(isEmpty()).append("\n");
                    break;
                case "front":
                    sb.append(front()).append("\n");
                    break;
                case "back":
                    sb.append(back()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    private static void push(int value) {
        queue[last++] = value;
    }

    private static int pop() {
        if (first == last) {
            return -1;
        }
        return queue[first++];
    }

    private static int isEmpty() {
        if (first == last) {
            return 1;
        }
        return 0;
    }

    private static int front() {
        if (first == last) {
            return -1;
        }
        return queue[first];
    }

    private static int back() {
        if (first == last) {
            return -1;
        }
        return queue[last - 1];
    }
}