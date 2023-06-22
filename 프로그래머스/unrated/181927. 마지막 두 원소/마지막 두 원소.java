class Solution {
    public int[] solution(int[] num_list) {
        int N = num_list.length;
        int[] answer = new int[N + 1];
        for (int i = 0; i < N; i++) answer[i] = num_list[i];
        answer[N] = num_list[N - 1] > num_list[N - 2] ? num_list[N - 1] - num_list[N - 2] : num_list[N - 1] * 2; 
        return answer;
    }
}