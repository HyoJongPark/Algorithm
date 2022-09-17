class Solution {
    static int N;
    static boolean[] check;
    static int[][] computers;

    public int solution(int n, int[][] computers) {
        int answer = 0;
        N = n;
        Solution.computers = computers;
        check = new boolean[N];
        for (int i = 0; i < n; i++) {
            if (!check[i]) {
                answer++;
                DFS(i);
            }
        }

        return answer;
    }

    private void DFS(int current) {
        for (int i = 0; i < N; i++) {
            if (!check[i] && computers[current][i] == 1) {
                check[i] = true;
                DFS(i);
            }
        }
    }
}