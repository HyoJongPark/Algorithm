import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        MQueue q = new MQueue();
        StringBuilder sb = new StringBuilder();
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();
            switch (command) {
                case "push":
                    q.push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(q.pop()).append("\n");
                    break;
                case "size":
                    sb.append(q.size()).append("\n");
                    break;
                case "empty":
                    sb.append(q.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "front":
                    sb.append(q.getFirst()).append("\n");
                    break;
                case "back":
                    sb.append(q.getLast()).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }

    static class MQueue {
        Node first;
        Node last;
        int size = 0;

        public void push(int x) {
            Node current = new Node(x);
            if (++size == 1) {
                first = current;
                last = current;
                return;
            }

            last.next = current;
            current.before = last;
            last = current;
        }

        public int pop() {
            if (isEmpty()) {
                return -1;
            }

            Node target = first;
            if (--size == 0) {
                first = null;
                last = null;
                return target.data;
            }

            first = first.next;
            first.before = null;
            return target.data;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }

        public int getFirst() {
            if (isEmpty()) {
                return -1;
            }
            return first.data;
        }

        public int getLast() {
            if (isEmpty()) {
                return -1;
            }
            return last.data;
        }

        static class Node {
            int data;
            Node next, before;

            public Node(int data) {
                this.data = data;
            }
        }
    }
}
