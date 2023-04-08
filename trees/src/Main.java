package trees.src;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Scanner sc;
    private static BufferedWriter bw;

    public static void main(String[] args) throws IOException {
        sc = new Scanner(System.in);
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        AVLTree avlTree = new AVLTree();
        Random random = new Random(System.currentTimeMillis());
        final int MAX_NODES = 30;

        for (int values = random.nextInt(1, MAX_NODES + 1), i = 0; i < values; ++i) {
            int val = 0;

            while (!isThreeDigit(val)) {
                val = random.nextInt(-100, 1000);
            }
            avlTree.insert(val);
        }
        TreeVisualizer tv = new TreeVisualizer();

        System.out.println(tv.forRoot(avlTree.getRoot()));
        sc.close();
        bw.flush();
    }

    private static boolean isThreeDigit(int val) {
        return (Integer.toString(val).length() == 3);
    }
}