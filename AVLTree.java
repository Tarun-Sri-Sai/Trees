
//  AVL Tree Implementation - Java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AVLTree {
    private TreeNode root;

    private static final int MAX_HEIGHT = 15;
    private static int[] startSpacing = null, middleSpacing = null;

    public TreeNode getRoot() {
        return root;
    }

    public AVLTree() {
        root = null;
    }

    public void delete(int data) {
        root = delete(root, data);
    }

    private static TreeNode delete(TreeNode root, int data) {
        if (root == null) {                                                             // empty tree
            return root;
        }
        if (data < root.getData()) {                                                    // target on left side of tree
            root.setLeft(delete(root.getLeft(), data));
        } else if (data > root.getData()) {                                             // target on right side of tree
            root.setRight(delete(root.getRight(), data));
        } else if (root.getLeft() == null || root.getRight() == null) {                 // root is target, but less than two children
            root = root.getLeft() != null ? root.getLeft() : root.getRight();
        } else {                                                                        // root is target, but two children
            root.setData(getMax(root.getLeft()));
            root.setLeft(delete(root.getLeft(), root.getData()));
        }
        if (root == null) {
            return root;
        }
        root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1); // update height for balanceFactor purpose
        int rootBalanceFactor = getBalanceFactor(root);
        if (rootBalanceFactor > 1) {                                                    // if left subtree is unbalanced
            int leftBalanceFactor = getBalanceFactor(root.getLeft());
            if (leftBalanceFactor < 0) {                                                // for LR rotation
                root.setLeft(leftRotate(root.getLeft()));
            }
            root = rightRotate(root);
            return root;
        }
        if (rootBalanceFactor < -1) {                                                   // if right subtree is unbalanced
            int rightBalanceFactor = getBalanceFactor(root.getRight());
            if (rightBalanceFactor > 0) {
                root.setRight(rightRotate(root.getRight()));                            // for RL rotation
            }
            root = leftRotate(root);
            return root;
        }
        return root;
    }

    private static int getMax(TreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        if (root.getRight() == null) {
            return root.getData();
        }
        int result = getMax(root.getRight());
        return result;
    }

    public boolean search(int data) {
        boolean result = search(root, data);
        return result;
    }

    private static boolean search(TreeNode root, int data) {
        if (root == null) {
            return false;
        }
        if (data < root.getData()) {
            boolean result = search(root.getLeft(), data);
            return result;
        }
        if (data > root.getData()) {
            boolean result = search(root.getRight(), data);
            return result;
        }
        return true;
    }

    public void insert(int data) {
        root = insert(root, data);
    }

    private static TreeNode insert(TreeNode root, int data) {
        if (root == null) {                                                             // create new node at target location
            return new TreeNode(data);
        }
        if (data < root.getData()) {                                                    // target in left subtree
            root.setLeft(insert(root.getLeft(), data));
        } else if (data > root.getData()) {                                             // target in right subtree
            root.setRight(insert(root.getRight(), data));
        } else {                                                                        // target already exists
            return root;
        }
        root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);  // update height for balanceFactor purpose
        int rootBalanceFactor = getBalanceFactor(root);
        if (rootBalanceFactor > 1) {                                                    // if left subtree is unbalanced
            if (data > root.getLeft().getData()) {                                      // LR case
                root.setLeft(leftRotate(root.getLeft()));
            }
            root = rightRotate(root);                                                   // right rotation necessary in both cases
            return root;
        }
        if (rootBalanceFactor < -1) {                                                   // if right subtree is unbalanced
            if (data < root.getRight().getData()) {                                     // RL case
                root.setRight(rightRotate(root.getRight()));
            }
            root = leftRotate(root);                                                    // left rotation necessary in both cases
            return root;
        }
        return root;
    }

    private static int getBalanceFactor(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int result = height(root.getLeft()) - height(root.getRight());
        return result;
    }

    private static int height(TreeNode root) {
        if (root == null) {
            return -1;
        }
        return root.getHeight();
    }

    private static TreeNode leftRotate(TreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.getRight() == null) {
            return root;
        }
        TreeNode rightChild = root.getRight();
        root.setRight(rightChild.getLeft());
        rightChild.setLeft(root);
        root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);
        rightChild.setHeight(Math.max(height(rightChild.getLeft()), height(rightChild.getRight())) + 1);
        return rightChild;
    }

    private static TreeNode rightRotate(TreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.getLeft() == null) {
            return root;
        }
        TreeNode leftChild = root.getLeft();
        root.setLeft(leftChild.getRight());
        leftChild.setRight(root);
        root.setHeight(Math.max(height(root.getLeft()), height(root.getRight())) + 1);
        leftChild.setHeight(Math.max(height(leftChild.getLeft()), height(leftChild.getRight())) + 1);
        return leftChild;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (startSpacing == null || middleSpacing == null) {
            init();
        }
        List<List<TreeNode>> levelList = getLevelOrder(root);
        int maxDepth = levelList.size() - 1;
        for (int i = 0; i <= maxDepth; i++) {
            List<TreeNode> nodeList = levelList.get(i);
            int currentHeight = maxDepth - i;
            result.append(new String(new char[startSpacing[currentHeight]]).replace("\0", " "));
            String middleSpace = new String(new char[middleSpacing[currentHeight]]).replace("\0", " ");
            for (int size = nodeList.size(), j = 0; j < size; j++) {
                String dataString = nodeList.get(j) == null ? "   " : String.format("%3d", nodeList.get(j).getData());
                result.append(dataString);
                if (j < size - 1) {
                    result.append(middleSpace);
                }
            }
            result.append(new String(new char[currentHeight]).replace("\0", "\n"));
        }
        String resultString = result.toString();
        return resultString;
    }

    private static void init() {
        startSpacing = new int[MAX_HEIGHT];
        startSpacing[0] = 0;
        for (int i = 1; i < 10; i++) {
            startSpacing[i] = startSpacing[i - 1] * 2 + 2;
        }
        middleSpacing = new int[MAX_HEIGHT];
        middleSpacing[0] = 1;
        for (int i = 1; i < 10; i++) {
            middleSpacing[i] = middleSpacing[i - 1] + (1 << (i + 1));
        }
    }

    private static List<List<TreeNode>> getLevelOrder(TreeNode root) {
        List<List<TreeNode>> result = new ArrayList<>();
        result.add(new ArrayList<>(Arrays.asList(root)));
        int currentDepth = 0;
        while (currentDepth < result.size()) {
            List<TreeNode> currentList = result.get(currentDepth++);
            List<TreeNode> nextList = new ArrayList<>();
            for (TreeNode currentNode : currentList) {
                nextList.add(currentNode != null ? currentNode.getLeft() : null);
                nextList.add(currentNode != null ? currentNode.getRight() : null);
            }
            if (notAllNull(nextList))
                result.add(nextList);
        }
        return result;
    }

    private static boolean notAllNull(List<TreeNode> list) {
        for (TreeNode ele : list) {
            if (ele != null) {
                return true;
            }
        }
        return false;
    }
}