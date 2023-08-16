package lib;
public class TreeNode<T> {
    public T value;
    public TreeNode<T> left, right;

    public TreeNode(T value) {
        this.value = value;
        this.left = this.right = null;
    }
}
