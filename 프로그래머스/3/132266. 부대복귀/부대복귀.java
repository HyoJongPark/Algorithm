import java.util.*;

class Solution {
    List<Integer>[] nodes;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        nodes = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new ArrayList<>();
        }
        for (int[] road: roads) {
            nodes[road[0]].add(road[1]);
            nodes[road[1]].add(road[0]);
        }
        
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            if (sources[i] == destination) {
                answer[i] = 0;    
                continue;
            }
            
            int cost = bfs(sources[i], destination, n);
            answer[i] = cost;
        }
        return answer;
    }
    
    private int bfs(int start, int destination, int n) {
        Queue<int[]> q = new LinkedList<>();
        boolean[] check = new boolean[n + 1];
        
        q.offer(new int[]{start, 0});
        check[start] = true;
        while(!q.isEmpty()) {
            int[] current = q.poll();
            
            for (int next: nodes[current[0]]) {
                if (next == destination) return current[1] + 1;
                
                if (!check[next]) {
                    q.offer(new int[]{next, current[1] + 1});
                    check[next] = true;   
                }
            }
        }
        return -1;
    }
}