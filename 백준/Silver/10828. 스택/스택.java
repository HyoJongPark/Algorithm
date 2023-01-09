import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N, index = 0;
    static int[] stack;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        stack = new int[N];

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
                    sb.append(index).append("\n");
                    break;
                case "empty":
                    sb.append(isEmpty()).append("\n");
                    break;
                case "top":
                    sb.append(top()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    private static void push(int value) {
        stack[index++] = value;
    }

    private static int pop() {
        if (index == 0) {
            return -1;
        }
        return stack[--index];
    }

    private static int isEmpty() {
        if (index == 0) {
            return 1;
        }
        return 0;
    }

    private static int top() {
        if (index == 0) {
            return -1;
        }
        return stack[index - 1];
    }
}