import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    static int N;
    static int[][] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        tree = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                tree[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //플로이드
        for (int t = 0; t < N; t++) { //중간 노드
            for (int i = 0; i < N; i++) { //시작 노드
                for (int j = 0; j < N; j++) { //도착 노드
                    if (tree[i][t] == 1 && tree[t][j] == 1) {
                        tree[i][j] = 1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(tree[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}