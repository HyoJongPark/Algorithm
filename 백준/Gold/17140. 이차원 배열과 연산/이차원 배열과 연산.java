import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

class Main {
    static int R, C, K, result = 0, maxX = 3, maxY = 3;
    static int[][] board = new int[100][100];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()) - 1;
        C = Integer.parseInt(st.nextToken()) - 1;
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (board[R][C] != K && result <= 100) {
            result++;

            if (maxY <= maxX) {
                commandR();
            } else {
                commandC();
            }
        }

        if (result == 101) result = -1;
        System.out.println(result);
    }

    private static void commandC() {
        int max = maxX;
        int[][] newBoard = new int[100][100];

        for (int y = 0; y < maxY; y++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            
            for (int x = 0; x < maxX; x++) {
                if (board[x][y] == 0) continue;

                map.put(board[x][y], map.getOrDefault(board[x][y], 0) + 1);
            }

            List<Map.Entry<Integer, Integer>> sortedResult = map.entrySet().stream().sorted(new Comparator<Map.Entry<Integer, Integer>>() {
                @Override
                public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                    if (Objects.equals(o1.getValue(), o2.getValue())) return o1.getKey() - o2.getKey();
                    return o1.getValue() - o2.getValue();
                }
            }).collect(Collectors.toList());

            int x = 0;
            for (Map.Entry<Integer, Integer> entrySet : sortedResult) {
                newBoard[x++][y] = entrySet.getKey();
                newBoard[x++][y] = entrySet.getValue();

                if (x == 100) {
                    break;
                }
            }
            max = Math.max(x, max);
        }
        maxX = Math.max(max, maxX);
        board = newBoard;
    }

    private static void commandR() {
        int max = maxY;
        int[][] newBoard = new int[100][100];

        for (int x = 0; x < maxX; x++) {
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int y = 0; y < maxY; y++) {
                if (board[x][y] == 0) continue;

                map.put(board[x][y], map.getOrDefault(board[x][y], 0) + 1);
            }

            List<Map.Entry<Integer, Integer>> sortedResult = map.entrySet().stream().sorted(new Comparator<Map.Entry<Integer, Integer>>() {
                @Override
                public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                    if (Objects.equals(o1.getValue(), o2.getValue())) return o1.getKey() - o2.getKey();
                    return o1.getValue() - o2.getValue();
                }
            }).collect(Collectors.toList());

            int y = 0;
            for (Map.Entry<Integer, Integer> entrySet : sortedResult) {
                newBoard[x][y++] = entrySet.getKey();
                newBoard[x][y++] = entrySet.getValue();

                if (y == 100) {
                    break;
                }
            }
            for (; y < maxY; y++) board[x][y] = 0;
            max = Math.max(y, max);
        }
        maxY = Math.max(max, maxY);
        board = newBoard;
    }
}
