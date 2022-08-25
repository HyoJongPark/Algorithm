class Solution {
    public long solution(int n) {
        long numberOfRoot[] = new long[2001];
        numberOfRoot[1] = 1;
        numberOfRoot[2] = 2;

        for (int i = 3; i <= 2000; i++)
            numberOfRoot[i] = (numberOfRoot[i - 1] + numberOfRoot[i - 2]) % 1234567;
        return numberOfRoot[n];
    }
}