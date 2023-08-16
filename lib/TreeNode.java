package lib;

public class TreeNode {
    public int value, height;
    public TreeNode left, right;

    public TreeNode(int value) {
        this.value = value;
        this.height = 0;
        this.left = this.right = null;
    }
}