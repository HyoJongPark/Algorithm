import java.util.*;

class Solution {
    public static int solution(String str1, String str2) {
        ArrayList<String> list1 = toStringArray(str1.toLowerCase());
        ArrayList<String> list2 = toStringArray(str2.toLowerCase());

        ArrayList<String> difference = new ArrayList<>();
        ArrayList<String> union = new ArrayList<>(list1);

        for(String str : list1) {
            if(list2.remove(str)) difference.add(str);
        }
        union.addAll(list2);

        int differenceSize = difference.size();
        int unionSize = union.size();
        return unionSize == 0 ? 65536 : (int) (((double)differenceSize / unionSize) * 65536);
    }

    private static ArrayList<String> toStringArray(String str) {
        char[] chars = str.toCharArray();
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < chars.length - 1; i++) {
            if (Character.isLetter(chars[i]) && Character.isLetter(chars[i + 1])) {
                strings.add(String.format("%s%s", chars[i], chars[i + 1]));
            }
        }
        Collections.sort(strings);
        return strings;
    }
}