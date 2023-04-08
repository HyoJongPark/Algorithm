import java.util.Arrays;

class Solution {

    public int solution(String dartResult) {
        int index = 0, resultIndex = 0;
        int[] results = new int[3];

        while (resultIndex < 3) {
            //findNumber
            int current = Integer.parseInt(String.valueOf(dartResult.charAt(index++)));
            if (Character.isDigit(dartResult.charAt(index))) {
                current = 10;
                index++;
            }

            //calculate first
            if (dartResult.charAt(index) == 'S') {
                current = (int) Math.pow(current, 1);
            } else if (dartResult.charAt(index) == 'D') {
                current = (int) Math.pow(current, 2);
            } else {
                current = (int) Math.pow(current, 3);
            }

            //calculate award score
            if (++index < dartResult.length() && !Character.isDigit(dartResult.charAt(index))) {
                if (dartResult.charAt(index) == '*') {
                    if (resultIndex == 0) {
                        current *= 2;
                    } else {
                        current *= 2;
                        results[resultIndex - 1] *= 2;
                    }
                } else {
                    current *= -1;
                }
                index++;
            }


            results[resultIndex++] = current;
        }

        return Arrays.stream(results)
                .sum();
    }
}