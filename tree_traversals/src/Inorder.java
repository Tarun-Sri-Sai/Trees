package src;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import lib.TreeNode;

public class Inorder<T> implements Iterator<T> {
    private TreeNode<T> current;
    private Stack<TreeNode<T>> stack;

    public Inorder(TreeNode<T> root) {
        this.current = root;
        this.stack = new Stack<>();
    }

    public boolean hasNext() {
        return this.current != null || !this.stack.isEmpty();
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (this.current != null) {
            this.stack.push(this.current);
            this.current = this.current.left;
        }
        this.current = this.stack.pop();
        T result = this.current.value;
        this.current = this.current.right;
        return result;
    }
}
