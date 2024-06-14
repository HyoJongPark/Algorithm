import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N, M, PIV = 1, INF = 1_000_000_001;
    static int[] tree, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        while (PIV <= N) {
            PIV = PIV << 1;
        }
        arr = new int[N];
        tree = new int[2 * PIV];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        makeTree();

        M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int command = Integer.parseInt(st.nextToken());
            if (command == 1) {
                update(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else if (command == 2) {
                sb.append(count(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), false))
                    .append("\n");
            } else {
                sb.append(count(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), true)).append("\n");
            }
        }
        System.out.print(sb);
    }

    //홀수 갯수로 초기화
    private static void makeTree() {
        for (int i = PIV; i < PIV + N; i++) {
            tree[i] = arr[i - PIV] % 2;
        }
        int start = PIV / 2, end = PIV;

        while (start != 0) {
            for (int i = start; i < end; i++) {
                tree[i] = tree[2 * i] + tree[2 * i + 1];
            }

            start /= 2;
            end /= 2;
        }
    }

    private static int count(int start, int end, boolean isEven) {
        start += PIV - 1;
        end += PIV - 1;

        int count = 0, totalLength = end - start + 1;
        while (start <= end) {
            if (start % 2 == 1) {
                count += tree[start++];
            }
            if (end % 2 == 0) {
                count += tree[end--];
            }

            start /= 2;
            end /= 2;
        }

        if (isEven) {
            return count;
        }
        return totalLength - count;
    }

    private static void update(int index, int value) {
        index += PIV - 1;
        int diff = (value % 2) - tree[index];
        if (diff == 0) {
            return;
        }
        tree[index] += diff;

        while (index != 0) {
            tree[index / 2] += diff;

            index /= 2;
        }
    }
}
