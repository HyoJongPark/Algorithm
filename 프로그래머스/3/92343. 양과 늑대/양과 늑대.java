import java.util.*;

class Solution {
    private int result = Integer.MIN_VALUE;
    private int[] info;
    private int[][] edges;

    public int solution(int[] info, int[][] edges) {
        this.edges = edges;
        this.info = info;

        DFS(0, 0, 0, new boolean[17]);
        return result;
    }

    private void DFS(int numberOfSheep, int numberOfWolf, int currentNode, boolean[] check) {
        check[currentNode] = true;
        if (info[currentNode] == 0) {
            numberOfSheep++;
            result = Math.max(result, numberOfSheep);
        } else if (numberOfSheep <= ++numberOfWolf) {
            return;
        }

        for (int[] next: edges) {
            if (check[next[0]] && !check[next[1]]) {
                boolean[] newCheck = new boolean[17];
                DFS(numberOfSheep, numberOfWolf, next[1], Arrays.copyOf(check, 17));  
            }
        }
    }
}