package Problems.TraverseTreeZigZag;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        traverseBTInZigZag(node1);
    }

    public static void traverseBTInZigZag(Node root) {
        if(root == null) return;

        Stack<Node> currentLevel = new Stack<>();
        Stack<Node> nextLevel = new Stack<>();
        currentLevel.push(root);
        boolean isRight = true;

        while(currentLevel.size() > 0) {
            Node current = currentLevel.pop();
            System.out.println(current.value);

            if(isRight) {
                if(current.left != null) nextLevel.push(current.left);
                if(current.right != null) nextLevel.push(current.right);
            } else {
                if(current.right != null) nextLevel.push(current.right);
                if(current.left != null) nextLevel.push(current.left);
            }

            if(currentLevel.size() <= 0) {
                isRight = !isRight;
                Stack<Node> temp = currentLevel;
                currentLevel = nextLevel;
                nextLevel = temp;
            }
        }
    }
}
