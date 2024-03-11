import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class Main {
    static int N, M;
    static int[][] students;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        students = new int[N][3]; //{학생 번호, 투표 수, 시간}

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            int studentNum = Integer.parseInt(st.nextToken());
            int idx = 0;

            for (int j = 0; j < N; j++) {
                if (students[j][0] == studentNum) {
                    students[j][1]++;
                    idx = j;
                    break;
                }
                if (students[j][0] == 0) {
                    idx = j;
                    break;
                }

                if (students[j][1] < students[idx][1] || (students[j][1] == students[idx][1] && students[j][2] < students[idx][2])) {
                    idx = j;
                }
            }

            if (students[idx][0] != studentNum) {
                students[idx][0] = studentNum;
                students[idx][1] = 1;
                students[idx][2] = i;
            }
        }

        Arrays.sort(students, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        StringBuilder sb = new StringBuilder();
        for (int[] student : students) {
            if (student[0] == 0) continue;

            sb.append(student[0]).append(" ");
        }
        System.out.println(sb);
    }
}
