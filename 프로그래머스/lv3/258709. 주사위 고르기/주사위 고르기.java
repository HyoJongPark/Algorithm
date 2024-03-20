import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    int max = Integer.MIN_VALUE;
    List<int[]> diceComb = new ArrayList<>();

    public static void main(String[] args) {
        new Solution().solution(new int[][]{{1, 2, 3, 4, 5, 6}, {3, 3, 3, 3, 4, 4}, {1, 3, 3, 4, 4, 4}, {1, 1, 4, 4, 5, 5}});
    }

    public int[] solution(int[][] dice) {
        //1. 모든 조합 구하기
        permutation(0, new int[dice.length / 2]);

        //2. 조합 마다 승률 구하기
        int[] result = calculateWinCount(dice);
        return Arrays.stream(result).map(num -> num + 1).toArray();
    }

    private int[] calculateWinCount(int[][] dice) {
        int[] result = null;

        for (int[] combA : diceComb) {
            int[] combB = new int[dice.length / 2];

            int combAIndex = 0, combBIndex = 0;
            for (int i = 0; i < dice.length; i++) {
                if (combAIndex < combA.length && combA[combAIndex] == i) combAIndex++;
                else combB[combBIndex++] = i;
            }

            List<Integer> aScore = new ArrayList<>();
            List<Integer> bScore = new ArrayList<>();

            combDice(0, 0, combA, dice, aScore);
            combDice(0, 0, combB, dice, bScore);

            aScore.sort(Integer::compareTo);
            bScore.sort(Integer::compareTo);

            int count = 0;
            for (Integer a : aScore) {
                int left = 0, right = bScore.size();

                while(left < right) {
                    int mid = (left + right) / 2;

                    if (a > bScore.get(mid)) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }

                count += left;
            }

            if (count > max) {
                max = count;
                result = combA;
            }
        }
        return result;
    }

    private void combDice(int level, int sum, int[] comb, int[][] dice, List<Integer> score) {
        if (level == comb.length) {
            score.add(sum);
            return;
        }

        for (int i = 0; i < 6; i++) {
            combDice(level + 1, sum + dice[comb[level]][i], comb, dice, score);
        }
    }

    private void permutation(int level, int[] comb) {
        if (level == comb.length) {
            diceComb.add(comb.clone());
            return;
        }

        int nextIndex = level == 0 ? 0 : comb[level - 1] + 1;
        for (; nextIndex < comb.length * 2; nextIndex++) {
            comb[level] = nextIndex;
            permutation(level + 1, comb);
        }
    }
}
