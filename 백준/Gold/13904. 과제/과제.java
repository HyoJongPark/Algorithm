import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        List<int[]> subjects = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            subjects.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        subjects.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int[] subject : subjects) {
            q.add(subject[1]);
            if(q.size() > subject[0]) {
                q.poll();
            }
        }

        int total = 0;
        while (!q.isEmpty()) {
            total += q.poll();
        }
        System.out.println(total);
    }
}
