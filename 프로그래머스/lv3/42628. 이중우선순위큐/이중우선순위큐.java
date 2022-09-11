import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {

    int queueSize;
    PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> min = new PriorityQueue<>();

    public int[] solution(String[] operations) {
        queueSize = 0;
        for (int i = 0; i < operations.length; i++) {
            String[] command = operations[i].split(" ");
            doCommand(command);
        }

        if (queueSize == 0) return new int[]{0, 0};
        return new int[]{max.poll(), min.poll()};
    }

    private void doCommand(String[] command) {
        if (queueSize == 0 && command[0].equals("D")) return;

        if (command[0].equals("I")) {
            max.offer(Integer.parseInt(command[1]));
            min.offer(Integer.parseInt(command[1]));
            queueSize++;
        } else if (command[0].equals("D") && command[1].equals("1")) {
            max.poll();
            queueSize--;
        } else {
            min.poll();
            queueSize--;
        }

        if (queueSize == 0) {
            max.clear();
            min.clear();
        }
    }
}