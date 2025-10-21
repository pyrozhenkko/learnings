class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x){}
}

public class Main {
    public static void main(String[] args) {

    }
}

class Solution{
    public TreeNode searchBST(TreeNode root, int val){
        if(root==null) return null;
        if(root.val == val) return root;

        if (root.val > val){
            root.left = searchBST(root.left, val);
        }
        if (root.val < val){
            root.right = searchBST(root.right, val);
        }
        return root;
    }
}
