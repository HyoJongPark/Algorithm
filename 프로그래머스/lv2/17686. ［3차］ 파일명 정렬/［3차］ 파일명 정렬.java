import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    public String[] solution(String[] files) {
        Arrays.sort(files, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String head1 = o1.split("[0-9]")[0];
                String head2 = o2.split("[0-9]")[0];

                if (head1.toLowerCase().equals(head2.toLowerCase())) {
                    String number1 = o1.split(head1)[1].split("[a-zA-Z.-]")[0];
                    String number2 = o2.split(head2)[1].split("[a-zA-Z.-]")[0];
                    return toInt(number1) - toInt(number2);
                }
                return head1.toLowerCase().compareTo(head2.toLowerCase());
            }
        });
        return files;
    }

    private static int toInt(String number) {
        return Integer.parseInt(number.replace(" ", ""));
    }
}