class Solution {
    static int N, count = 0;
    static int[] board;

    public int solution(int n) {
        N = n;
        board = new int[N];

        DFS(0);
        return count;
    }

    private void DFS(int level) {
        if (level == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            board[level] = i;
            if (isValid(level)) {
                DFS(level + 1);
            }
        }
    }

    private boolean isValid(int level) {
        for (int i = 0; i < level; i++) {
            if (board[i] == board[level] || level - i == Math.abs(board[level] - board[i])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int solution = new Solution().solution(4);
    }
}