import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] tmp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = tmp[0], M = tmp[1];

        HashSet<String> heard = new HashSet<>();
        ArrayList<String> answer = new ArrayList<>();
        for (int i = 0; i < N; i++) heard.add(br.readLine());
        for (int i = 0; i < M; i++){
            String see = br.readLine();
            if (heard.contains(see)) answer.add(see);
        }
        Collections.sort(answer);

        StringBuilder sb = new StringBuilder();
        sb.append(answer.size()).append("\n");
        for (String s : answer) {
            sb.append(s).append("\n");
        }
        System.out.println(sb);
    }
}