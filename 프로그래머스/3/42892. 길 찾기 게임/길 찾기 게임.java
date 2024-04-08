import java.util.*;

class Solution {
    
    static int depth = 0;
    static boolean[] check;
    
    public int[][] solution(int[][] nodeInfo) {
        Node[] nodes = new Node[nodeInfo.length];
        for (int i = 0; i < nodeInfo.length; i++) {
            int[] current = nodeInfo[i];
            nodes[i] = new Node(i + 1, current[0], current[1]);
        }
        
        Arrays.sort(nodes, (a, b) -> {
            if (a.y == b.y) return a.x - b.x;
            return b.y - a.y;
        });
        
        Node head = nodes[0];
        for (int i = 1; i < nodes.length; i++) {
            insertNode(head, nodes[i]);
        }
        
        int[][] answer = new int[2][nodes.length];
        check = new boolean[nodes.length + 1];
        preOrder(head, answer[0]);  //전위 순회
        
        check = new boolean[nodes.length + 1];
        depth = 0;
        postOrder(head, answer[1]); //후위 순회
        return answer;
    }
    
    private void insertNode(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) parent.left = child;
            else insertNode(parent.left, child);
        } else {
            if (parent.right == null) parent.right = child;
            else insertNode(parent.right, child);
        }
    }
    
    private void preOrder(Node node, int[] history) {
        history[depth++] = node.nodeNo;
        
        if (node.left != null) {
            preOrder(node.left, history);
        }
        if (node.right != null) {
            preOrder(node.right, history);
        }
    }
    
    private void postOrder(Node node, int[] history) {
        if (node.left != null) {
            postOrder(node.left, history);
        }
        if (node.right != null) {
            postOrder(node.right, history);
        }
        history[depth++] = node.nodeNo;
    }
    
    static class Node {
        int nodeNo;
        int x, y;
        Node left, right;
        
        public Node (int nodeNo, int x, int y) {
            this.nodeNo = nodeNo;
            this.x = x;
            this.y = y;
        }
    }
} 