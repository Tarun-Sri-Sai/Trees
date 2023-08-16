package src;

import lib.AVLTreeNode;

//  AVL Tree Implementation - Java
public class AVLTree {
    public AVLTreeNode root;

    public AVLTree() {
        root = null;
    }

    public void delete(int data) {
        root = delete(root, data);
    }

    private static AVLTreeNode delete(AVLTreeNode root, int data) {

        // empty tree
        if (root == null) {
            return root;
        }

        // target on left side of tree
        if (data < root.value) {
            root.left = delete(root.left, data);
        }

        // target on right side of tree
        else if (data > root.value) {
            root.right = delete(root.right, data);
        }

        // root is target, but less than two children
        else if (root.left == null || root.right == null) {
            root = root.left != null ? root.left : root.right;
        }

        // root is target, but two children
        else {
            root.value = getMax(root.left);
            root.left = delete(root.left, root.value);
        }
        if (root == null) {
            return root;
        }

        // update height for balanceFactor purpose
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int rootBalanceFactor = getBalanceFactor(root);

        // if left subtree is unbalanced
        if (rootBalanceFactor > 1) {
            int leftBalanceFactor = getBalanceFactor(root.left);

            // for LR rotation
            if (leftBalanceFactor < 0) {
                root.left = leftRotate(root.left);
            }
            root = rightRotate(root);
            return root;
        }

        // if right subtree is unbalanced
        if (rootBalanceFactor < -1) {
            int rightBalanceFactor = getBalanceFactor(root.right);

            // for RL rotation
            if (rightBalanceFactor > 0) {
                root.right = rightRotate(root.right);
            }
            root = leftRotate(root);
            return root;
        }
        return root;
    }

    private static int getMax(AVLTreeNode root) {
        if (root == null) {
            return Integer.MIN_VALUE;
        }
        if (root.right == null) {
            return root.value;
        }
        int result = getMax(root.right);
        return result;
    }

    public boolean search(int data) {
        boolean result = search(root, data);
        return result;
    }

    private static boolean search(AVLTreeNode root, int data) {
        if (root == null) {
            return false;
        }
        if (data < root.value) {
            boolean result = search(root.left, data);
            return result;
        }
        if (data > root.value) {
            boolean result = search(root.right, data);
            return result;
        }
        return true;
    }

    public void insert(int data) {
        root = insert(root, data);
    }

    private static AVLTreeNode insert(AVLTreeNode root, int data) {

        // create new node at target location
        if (root == null) {
            return new AVLTreeNode(data);
        }

        // target in left subtree
        if (data < root.value) {
            root.left = insert(root.left, data);
        }

        // target in right subtree
        else if (data > root.value) {
            root.right = insert(root.right, data);

        }

        // target already exists
        else {
            return root;
        }

        // update height for balanceFactor purpose
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int rootBalanceFactor = getBalanceFactor(root);

        // if left subtree is unbalanced
        if (rootBalanceFactor > 1) {

            // LR case
            if (data > root.left.value) {
                root.left = leftRotate(root.left);
            }

            // right rotation necessary in both cases
            root = rightRotate(root);
            return root;
        }

        // if right subtree is unbalanced
        if (rootBalanceFactor < -1) {

            // RL case
            if (data < root.right.value) {
                root.right = rightRotate(root.right);
            }

            // left rotation necessary in both cases
            root = leftRotate(root);
            return root;
        }
        return root;
    }

    private static int getBalanceFactor(AVLTreeNode root) {
        if (root == null) {
            return 0;
        }
        int result = height(root.left) - height(root.right);
        return result;
    }

    private static int height(AVLTreeNode root) {
        if (root == null) {
            return -1;
        }
        return root.height;
    }

    private static AVLTreeNode leftRotate(AVLTreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.right == null) {
            return root;
        }
        AVLTreeNode rightChild = root.right;
        root.right = rightChild.left;
        rightChild.left = root;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        rightChild.height = Math.max(height(rightChild.left), height(rightChild.right)) + 1;
        return rightChild;
    }

    private static AVLTreeNode rightRotate(AVLTreeNode root) {
        if (root == null) {
            return root;
        }
        if (root.left == null) {
            return root;
        }
        AVLTreeNode leftChild = root.left;
        root.left = leftChild.right;
        leftChild.right = root;
        root.height = Math.max(height(root.left), height(root.right)) + 1;
        leftChild.height = Math.max(height(leftChild.left), height(leftChild.right)) + 1;
        return leftChild;
    }
}