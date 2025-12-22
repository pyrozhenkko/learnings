package org.ccpc;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

    }
}
class Node{
    int val;
    public List<Node> children;

    public Node(){}
    public Node(int val){
        this.val = val;
    }
    public Node(int val, List<Node> children){
        this.val = val;
        this.children = children;
    }
}

class Solution {
    List<Integer> output = new ArrayList<>();

    public List<Integer> preorder(Node root) {
        if (root == null) return output;

        // Root -> children
        output.add(root.val);

        if (root.children != null) {
            for (Node child : root.children) {
                preorder(child);
            }
        }
        return output;
    }
}
