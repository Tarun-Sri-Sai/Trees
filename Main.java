import java.util.Random;
import java.util.Scanner;

import src.AVLTree;
import src.TreeVisualizer;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AVLTree avlTree = new AVLTree();
        Random random = new Random(System.currentTimeMillis());
        final int MAX_NODES = 30;
        int values = random.nextInt(1, MAX_NODES + 1);
        for (int i = 0; i < values; ++i) {
            int val = 0;
            while (!isThreeDigit(val)) {
                val = random.nextInt(-100, 1000);
            }
            avlTree.insert(val);
        }
        TreeVisualizer tv = new TreeVisualizer();
        System.out.println(tv.forRoot(avlTree.root));
        sc.close();
    }

    private static boolean isThreeDigit(int val) {
        return (Integer.toString(val).length() == 3);
    }
}