package src;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import lib.TreeNode;

public class Preorder<T> implements Iterator<T> {
    private Stack<TreeNode<T>> stack;

    public Preorder(TreeNode<T> root) {
        this.stack = new Stack<>();
        if (root != null) {
            this.stack.push(root);
        }
    }

    public boolean hasNext() {
        return !this.stack.isEmpty();
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        TreeNode<T> current = this.stack.pop();
        T result = current.value;
        if (current.right != null) {
            this.stack.push(current.right);
        }
        if (current.left != null) {
            this.stack.push(current.left);
        }
        return result;
    }
}
