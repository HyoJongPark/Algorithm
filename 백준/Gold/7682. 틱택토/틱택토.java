import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int N = 3;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        StringBuilder sb = new StringBuilder();
        while (!(input = br.readLine()).equals("end")) {
            char[][] board = new char[N][N];
            int xCount = 0, oCount = 0;

            for (int j = 0; j < N; j++) {
                for (int i = 0; i < N; i++) {
                    board[i][j] = input.charAt(i * 3 + j);

                    if (board[i][j] == 'X') xCount++;
                    else if (board[i][j] == 'O') oCount++;
                }
            }

            if ((xCount != oCount && xCount - oCount != 1) || !isValidGame(board, xCount, oCount)) {
                sb.append("invalid\n");
            } else {
                sb.append("valid\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean isValidGame(char[][] board, int xCount, int oCount) {
        boolean xWin = false, oWin = false;

        //대각 체크
        if ((board[0][0] != '.' && board[0][0] == board[1][1] && board[1][1] == board[2][2])) {
            if (board[1][1] == 'X') xWin = true;
            if (board[1][1] == 'O') oWin = true;

        }
        if ((board[2][0] != '.' && board[2][0] == board[1][1] && board[1][1] == board[0][2])) {
            if (board[1][1] == 'X') xWin = true;
            if (board[1][1] == 'O') oWin = true;
        }

        //가로-세로 체크
        for (int i = 0; i < N; i++) {
            int countX = 1, countY = 1;
            char xTarget = board[i][0], yTarget = board[0][i];

            for (int j = 1; j < N; j++) {
                if (xTarget == board[i][j]) countX++;
                if (yTarget == board[j][i]) countY++;
            }

            if ((xTarget == 'X' && countX == 3) || (yTarget == 'X' && countY == 3)) xWin = true;
            if ((xTarget == 'O' && countX == 3) || (yTarget == 'O' && countY == 3)) oWin = true;
        }

        //O가 승리일 경우 O의 수는 X의 수와 동일해야한다.
        if (oWin && xCount > oCount) return false;
        //X가 승리일 경우 X의 수는 Y보다 하나 많다.
        if (xWin && xCount <= oCount) return false;
        //빈 칸이 존재하고, 아무도 승리하지 않았을 경우 최종 상태 아님
        if (xCount + oCount != 9 && (!xWin && !oWin)) return false;
        //x와 o는 동시 승리가 불가능
        return !(xWin && oWin);
    }
}
