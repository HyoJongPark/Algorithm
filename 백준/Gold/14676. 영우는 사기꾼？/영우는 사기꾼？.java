import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Main {

    static int N, M, K;
    static List<Integer>[] relation;
    static int[] inDegree, check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        relation = new List[N + 1];
        inDegree = new int[N + 1];
        check = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            relation[i] = new ArrayList();
        }

        for (; M > 0; M--) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            relation[a].add(b);
            inDegree[b]++;
        }

        boolean isFailure = false;
        for (; K > 0; K--) {
            st = new StringTokenizer(br.readLine());

            if (st.nextToken().equals("1")) {
                boolean buildResult = build(Integer.parseInt(st.nextToken()));

                if (!buildResult) {
                    isFailure = true;
                    break;
                }
            } else {
                boolean destroyResult = destroy(Integer.parseInt(st.nextToken()));

                if (!destroyResult) {
                    isFailure = true;
                    break;
                }
            }
        }

        if (isFailure) {
            System.out.println("Lier!");
        } else {
            System.out.println("King-God-Emperor");
        }
    }

    private static boolean destroy(int buildingNum) {
        if (check[buildingNum] == 0) return false;
        if (--check[buildingNum] != 0) return true;

        for (int next : relation[buildingNum]) {
            inDegree[next]++;
        }
        return true;
    }

    private static boolean build(int buildingNum) {
        if (inDegree[buildingNum] != 0) return false;
        if (++check[buildingNum] > 1) return true;

        for (int next : relation[buildingNum]) {
            inDegree[next]--;
        }
        return true;
    }
}
