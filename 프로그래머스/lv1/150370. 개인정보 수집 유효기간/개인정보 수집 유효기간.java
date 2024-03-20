import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("2022.05.19", new String[]{"A 6", "B 12", "C 3"}, new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C"}));
    }

    private static final int DAY_OF_MONTH = 28;

    int toDay;
    HashMap<Character, Integer> terms = new HashMap<>();

    public int[] solution(String today, String[] terms, String[] privacies) {
        for (String term : terms) {
            String[] termInfo = term.split(" ");

            this.terms.put(termInfo[0].toCharArray()[0], DAY_OF_MONTH * Integer.parseInt(termInfo[1]));
        }
        this.toDay = convertDateFormatToDateCount(today);
        return findExpiredInfo(privacies);
    }

    private int[] findExpiredInfo(String[] privacies) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < privacies.length; i++) {
            if (isExpiredInfo(privacies[i])) {
                result.add(i + 1);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private boolean isExpiredInfo(String privacy) {
        String[] privacyInfo = privacy.split(" ");
        int expireDate = convertDateFormatToDateCount(privacyInfo[0]) + terms.get(privacyInfo[1].toCharArray()[0]);

        return toDay >= expireDate;
    }

    private int convertDateFormatToDateCount(String date) {
        String[] dateInfo = date.split("\\.");

        int dayCount = Integer.parseInt(dateInfo[2]);
        dayCount += Integer.parseInt(dateInfo[1]) * DAY_OF_MONTH;
        dayCount += Integer.parseInt(dateInfo[0]) * 12 * DAY_OF_MONTH;
        return dayCount;
    }
}
