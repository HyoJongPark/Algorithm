class Solution {
    public int[] solution(int brown, int yellow) {
        int layer = 0, result = 0;
        do {
            if (yellow % ++layer != 0) continue;

            result = 2 * (yellow / layer) + (2 * layer) + 4;
        } while(result != brown);
        
        
        return new int[]{Math.max((yellow / layer), layer) + 2, Math.min((yellow / layer), layer) + 2};
    }
}