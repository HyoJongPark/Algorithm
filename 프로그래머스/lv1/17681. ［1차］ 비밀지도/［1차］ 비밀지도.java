class Solution {

    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];

        char[][] result1 = makeResult(n, arr1, null);
        char[][] result2 = makeResult(n, arr2, result1);

        for (int i = 0; i < n; i++) {
            answer[i] = String.valueOf(result2[i]);
        }
        return answer;
    }

    private char[][] makeResult(int n, int[] arr1, char[][] beforeResult) {
        char[][] result = beforeResult;
        if (result == null) {
            result = new char[n][n];
        }

        for (int i = 0; i < n; i++) {
            StringBuilder binaryString = new StringBuilder(Integer.toBinaryString(arr1[i]));
            while (binaryString.length() < n) {
                binaryString.insert(0, '0');
            }

            for (int j = 0; j < n; j++) {
                if (binaryString.charAt(j) == '1' || result[i][j] == '#') {
                    result[i][j] = '#';
                } else {
                    result[i][j] = ' ';
                }
            }
        }
        return result;
    }
}