import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int N;
    static Student[][] board;
    static Queue<Student> students = new LinkedList<>();

    static final int[][] d = {{-1, 0},{0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new Student[N][N];

        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Student student = new Student(Integer.parseInt(st.nextToken()));

            HashSet<Integer> favoriteStudent = new HashSet<>();
            for (int j = 0; j < 4; j++) {
                favoriteStudent.add(Integer.parseInt(st.nextToken()));
            }
            student.favoriteStudents = favoriteStudent;
            students.add(student);
        }

        /*
         * 1. 인접 칸에 선호학생이 많은 자리
         * 2. 여러개면, 빈자리가 많은
         * 3. 행 번호 작은
         */
        while (!students.isEmpty()) {
            Student current = students.poll();
            int[] point = searchTile(current);

            board[point[0]][point[1]] = current;
        }

        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                result += calculateResult(i, j, board[i][j]);
            }
        }
        System.out.println(result);
    }

    private static int[] searchTile(Student current) {
        int[] point = new int[2];
        int nearestStudent = -1, nearEmptyTile = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == null) {
                    int[] count = findNearestStudent(i, j, current.favoriteStudents);

                    if (count[0] > nearestStudent) {
                        point[0] = i;
                        point[1] = j;
                        nearestStudent = count[0];
                        nearEmptyTile = count[1];
                    } else if (count[0] == nearestStudent && count[1] > nearEmptyTile) {
                        point[0] = i;
                        point[1] = j;
                        nearEmptyTile = count[1];
                    }
                }
            }
        }
        return point;
    }

    private static int calculateResult(int x, int y, Student student) {
        int favoriteScore = 0;
        for (int i = 0; i < d.length; i++) {
            int nextX = x + d[i][0], nextY = y + d[i][1];

            if (isValid(nextX, nextY) && board[nextX][nextY] != null && student.favoriteStudents.contains(board[nextX][nextY].studentNo)) {
                favoriteScore += 1;
            }
        }

        if (favoriteScore == 2) return 10;
        else if (favoriteScore == 3) return 100;
        else if (favoriteScore == 4) return 1000;
        return favoriteScore;
    }

    private static int[] findNearestStudent(int x, int y, HashSet<Integer> favoriteStudents) {
        //count[0] = numberOfNearestStudent, count[1] = numberOfNearestEmptyTile
        int[] count = new int[2];
        for (int i = 0; i < d.length; i++) {
            int nextX = x + d[i][0], nextY = y + d[i][1];

            if (isValid(nextX, nextY)) {
                if (board[nextX][nextY] == null) count[1] += 1;
                else if (favoriteStudents.contains(board[nextX][nextY].studentNo)) count[0] += 1;
            }
        }
        return count;
    }

    private static boolean isValid(int nextX, int nextY) {
        return 0 <= nextX && nextX < N && 0 <= nextY && nextY < N;
    }

    static class Student {
        int studentNo;
        HashSet<Integer> favoriteStudents;

        public Student(int studentNo) {
            this.studentNo = studentNo;
        }
    }
}
