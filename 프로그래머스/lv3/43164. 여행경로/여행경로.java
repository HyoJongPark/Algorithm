import java.util.*;

class Solution {

    boolean[] visited;
    String[][] tickets;
    List<String> allRoute = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        this.tickets = tickets;

        DFS("ICN", "ICN", 0);
        Collections.sort(allRoute);

        return allRoute.get(0).split(" ");
    }

    public void DFS(String current, String route, int cnt) {
        if (cnt == tickets.length) {
            allRoute.add(route);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (current.equals(tickets[i][0]) && !visited[i]) {
                visited[i] = true;
                DFS(tickets[i][1], route + " " + tickets[i][1], cnt + 1);
                visited[i] = false;
            }
        }
    }
}