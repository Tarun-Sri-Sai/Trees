package src;

//  TreeNode Visualizer
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lib.AVLTreeNode;

public class TreeVisualizer {
    private AVLTreeNode root;
    private final int MAX_HEIGHT = 15;
    private int[] startSpacing = null, middleSpacing = null;

    public TreeVisualizer() {
        root = null;
        startSpacing = new int[MAX_HEIGHT];
        startSpacing[0] = 0;
        for (int i = 1; i < 10; ++i) {
            startSpacing[i] = startSpacing[i - 1] * 2 + 2;
        }
        middleSpacing = new int[MAX_HEIGHT];
        middleSpacing[0] = 1;
        for (int i = 1; i < 10; ++i) {
            middleSpacing[i] = middleSpacing[i - 1] + (1 << (i + 1));
        }
    }

    public TreeVisualizer forRoot(AVLTreeNode root) {
        this.root = root;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        List<List<AVLTreeNode>> levelList = getLevelOrder();
        int maxDepth = levelList.size() - 1;
        for (int i = 0; i <= maxDepth; ++i) {
            List<AVLTreeNode> nodeList = levelList.get(i);
            int currentHeight = maxDepth - i;
            result.append(new String(new char[startSpacing[currentHeight]]).replace("\0", " "));
            String middleSpace = new String(new char[middleSpacing[currentHeight]]).replace("\0", " ");
            for (int size = nodeList.size(), j = 0; j < size; ++j) {
                String dataString = nodeList.get(j) == null? "   ": String.format("%3d", nodeList.get(j).value);
                result.append(dataString);
                if (j < size - 1) {
                    result.append(middleSpace);
                }
            }
            result.append(new String(new char[currentHeight]).replace("\0", "\n"));
        }
        return result.toString();
    }

    private List<List<AVLTreeNode>> getLevelOrder() {
        List<List<AVLTreeNode>> result = new ArrayList<>();
        result.add(new ArrayList<>(Arrays.asList(root)));
        int currentDepth = 0;
        while (currentDepth < result.size()) {
            List<AVLTreeNode> currentList = result.get(currentDepth++);
            List<AVLTreeNode> nextList = new ArrayList<>();
            for (AVLTreeNode currentNode : currentList) {
                if (currentNode == null) {
                    nextList.add(null);
                    nextList.add(null);
                    continue;
                }
                nextList.add(currentNode.left);
                nextList.add(currentNode.right);
            }
            if (isNotAllNull(nextList)) {
                result.add(nextList);
            }
        }
        return result;
    }

    private boolean isNotAllNull(List<AVLTreeNode> list) {
        for (AVLTreeNode ele : list) {
            if (ele != null) {
                return true;
            }
        }
        return false;
    }
}