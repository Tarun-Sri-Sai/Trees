package lib;
import java.util.Comparator;
import java.util.List;

public class Tree<T> {
    public TreeNode<T> root;
    private Comparator<T> cmp;

    public Tree(Comparator<T> cmp) {
        this.root = null;
        this.cmp = cmp;
    }

    public void populate(List<T> list) {
        for (T ele : list) {
            this.add(ele);
        }
    }

    public void add(T value) {
        this.root = addToRoot(this.root, value);
    }

    private TreeNode<T> addToRoot(TreeNode<T> root, T value) {
        if (root == null) {
            return new TreeNode<T>(value);
        }
        int diff = this.cmp.compare(value, root.value);
        if (diff < 0) {
            root.left = addToRoot(root.left, value);
            return root;
        }
        if (diff > 0) {
            root.right = addToRoot(root.right, value);
            return root;
        }
        return root;
    }

    public void remove(T value) {
        this.root = removeFromRoot(this.root, value);
    }

    private TreeNode<T> removeFromRoot(TreeNode<T> root, T value) {
        if (root == null) {
            return null;
        }
        int diff = this.cmp.compare(value, root.value);
        if (diff < 0) {
            root.left = removeFromRoot(root.left, value);
            return root;
        }
        if (diff > 0) {
            root.right = removeFromRoot(root.right, value);
            return root;
        }
        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }
        T bestValue = getBestSuccessor(root.left);
        root.value = bestValue;
        root.left = removeFromRoot(root.left, bestValue);
        return root;
    }

    private T getBestSuccessor(TreeNode<T> root) {
        TreeNode<T> current = root;
        while (current.right != null) {
            current = current.right;
        }
        return current.value;
    }
}
