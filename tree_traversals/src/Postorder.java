package src;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import lib.TreeNode;

public class Postorder<T> implements Iterator<T> {
    Stack<TreeNode<T>> stack;

    public Postorder(TreeNode<T> root) {
        Stack<TreeNode<T>> tempStack = new Stack<>();
        this.stack = new Stack<>();
        if (root != null) {
            tempStack.push(root);
        }
        while (!tempStack.isEmpty()) {
            TreeNode<T> current = tempStack.pop();
            this.stack.push(current);
            if (current.left != null) {
                tempStack.push(current.left);
            }
            if (current.right != null) {
                tempStack.push(current.right);
            }
        }
    }

    public boolean hasNext() {
        return !this.stack.isEmpty();
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return this.stack.pop().value;
    }
}
