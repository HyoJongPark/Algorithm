import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

class Main {

    static int result = 0;
    static char[] roomNumber;
    static HashMap<Integer, Integer> cardCount = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] roomNumber = br.readLine().toCharArray();

        buyCard();
        for (char c : roomNumber) {
            int currentNumber = c - '0';

            int count = cardCount.get(currentNumber);
            if (count == 0 && (currentNumber == 9 || currentNumber == 6)) {
                currentNumber = currentNumber == 9 ? 6 : 9;

                count = cardCount.get(currentNumber);
            }

            if (count == 0) {
                buyAndUseCard(currentNumber);
            } else {
                cardCount.put(currentNumber, count - 1);
            }
        }
        System.out.println(result);
    }

    private static void buyAndUseCard(int currentNumber) {
        for (int i = 0; i <= 9; i++) {
            if (i == currentNumber) {
                continue;
            }

            cardCount.put(i, cardCount.getOrDefault(i, 0) + 1);
        }
        result++;
    }

    private static void buyCard() {
        for (int i = 0; i <= 9; i++) {
            cardCount.put(i, cardCount.getOrDefault(i, 0) + 1);
        }
        result++;
    }
}
