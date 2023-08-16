package lib;

public class AVLTreeNode {
    public int value, height;
    public AVLTreeNode left, right;

    public AVLTreeNode(int value) {
        this.value = value;
        this.height = 0;
        this.left = this.right = null;
    }
}