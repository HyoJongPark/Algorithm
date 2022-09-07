import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();
    static String MESSAGE_LINE_1 = "\"재귀함수가 뭔가요?\"\n";
    static String MESSAGE_LINE_2 = "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n";
    static String MESSAGE_LINE_3 = "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n";
    static String MESSAGE_LINE_4 = "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n";
    static String END_MESSAGE = "라고 답변하였지.\n";


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
        recursion(0);
        System.out.println(sb);
    }

    private static void recursion(int level) {
        if (level > N) return;

        StringBuilder firstWord = new StringBuilder();
        for (int i = 0; i < level; i++) firstWord.append("____");

        sb.append(firstWord).append(MESSAGE_LINE_1);
        if (level == N) {
            sb.append(firstWord).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
        } else {
            sb.append(firstWord).append(MESSAGE_LINE_2);
            sb.append(firstWord).append(MESSAGE_LINE_3);
            sb.append(firstWord).append(MESSAGE_LINE_4);
        }
        recursion(level + 1);
        sb.append(firstWord).append(END_MESSAGE);
    }
}