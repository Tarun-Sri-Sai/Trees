import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import lib.Tree;
import lib.TreeNode;
import src.Inorder;
import src.Postorder;
import src.Preorder;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = getRandomList(10, 100, 1000);
        Tree<Integer> tree = new Tree<>((i1, i2) -> i1 - i2);
        tree.populate(list);
        System.out.println();
        checkPreorder(tree);
        System.out.println();
        checkInorder(tree);
        System.out.println();
        checkPostorder(tree);
        System.out.println();
    }

    private static void checkPostorder(Tree<Integer> tree) {
        List<Integer> recursivePostorder = getPostorder(tree.root);
        List<Integer> iterativePostorder = new ArrayList<>();
        Postorder<Integer> preorder = new Postorder<>(tree.root);
        while (preorder.hasNext()) {
            iterativePostorder.add(preorder.next());
        }
        if (!iterativePostorder.equals(recursivePostorder)) {
            System.out.println("Postorder iterator failed\n" + recursivePostorder + "\n" + iterativePostorder);
        } else {
            System.out.println("Postorder iterator successful");
        }
    }

    private static <T> List<T> getPostorder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.addAll(getPostorder(root.left));
        result.addAll(getPostorder(root.right));
        result.add(root.value);
        return result;
    }

    private static void checkInorder(Tree<Integer> tree) {
        List<Integer> recursiveInorder = getInorder(tree.root);
        List<Integer> iterativeInorder = new ArrayList<>();
        Inorder<Integer> preorder = new Inorder<>(tree.root);
        while (preorder.hasNext()) {
            iterativeInorder.add(preorder.next());
        }
        if (!iterativeInorder.equals(recursiveInorder)) {
            System.out.println("Inorder iterator failed\n" + recursiveInorder + "\n" + iterativeInorder);
        } else {
            System.out.println("Inorder iterator successful");
        }
    }

    private static <T> List<T> getInorder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.addAll(getInorder(root.left));
        result.add(root.value);
        result.addAll(getInorder(root.right));
        return result;
    }

    private static void checkPreorder(Tree<Integer> tree) {
        List<Integer> recursivePreorder = getPreorder(tree.root);
        List<Integer> iterativePreorder = new ArrayList<>();
        Preorder<Integer> preorder = new Preorder<>(tree.root);
        while (preorder.hasNext()) {
            iterativePreorder.add(preorder.next());
        }
        if (!iterativePreorder.equals(recursivePreorder)) {
            System.out.println("Preorder iterator failed\n" + recursivePreorder + "\n" + iterativePreorder);
        } else {
            System.out.println("Preorder iterator successful");
        }
    }

    private static <T> List<T> getPreorder(TreeNode<T> root) {
        List<T> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.add(root.value);
        result.addAll(getPreorder(root.left));
        result.addAll(getPreorder(root.right));
        return result;
    }

    private static List<Integer> getRandomList(int length, int lowerBound, int upperBound) {
        List<Integer> result = new ArrayList<>();
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < length; i++) {
            result.add(random.nextInt(lowerBound, upperBound));
        }
        return result;
    }
}
