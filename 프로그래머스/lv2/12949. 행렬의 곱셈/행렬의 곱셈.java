class Solution {
    
    int N, M;
    int[][] arr1, arr2;
    
    public int[][] solution(int[][] arr1, int[][] arr2) {
        //[3 x 2] * [2 x 2] = [3 x 2]
        N = arr1.length;
        M = arr2[0].length;
        this.arr1 = arr1;
        this.arr2 = arr2;
        int[][] answer = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer[i][j] = multiply(i, j);
            }
        }
        return answer;
    }
    
    private int multiply(int x, int y) {
        int result = 0;
        int i = 0, j = 0;
        
        while (i < arr1[0].length && j < arr2.length) {
            result += arr1[x][i++] * arr2[j++][y];
        }        
        return result;
    }
}