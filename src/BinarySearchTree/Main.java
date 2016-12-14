

package BinarySearchTree;

import java.util.ArrayList;

class Main {
    private static TreeIterator iterator;

    public static void main(String[] args) {
        BinarySearchTree<Integer, Double> bsearchTree = new BinarySearchTree<Integer, Double>();
        SelfBalanceTree<Integer, Double> selfbTree = new SelfBalanceTree<Integer, Double>();
        BinaryTree<String, Double> binaryTree = new BinaryTree<String, Double>();

        bsearchTree.insert(47, 22.6);
        bsearchTree.insert(22, 16.3);
        bsearchTree.insert(50, 50.9);

        selfbTree.insert(10, 20.0);
        selfbTree.insert(15, 22.6);
        selfbTree.insert(20, 26.3);
        selfbTree.insert(25, 60.0);
        selfbTree.insert(44, 55.0);
        selfbTree.insert(17, 33.0);


        binaryTree.insert("D", 22.1, "", "");
        binaryTree.insert("B", 22.2, "D", "right");
        binaryTree.insert("A", 26.3, "B", "left");
        binaryTree.insert("C", 26.3, "A", "left");
        binaryTree.insert("H", 26.3, "A", "right");

        test(binaryTree);
        test(bsearchTree);
        test(selfbTree);
        iterator = selfbTree.iterator();
        while (iterator.hasNext()) {
            if(iterator.next().key==Integer.valueOf(17)){
                iterator.remove();
            }
        }
        test(selfbTree);
    }

    private static void test(BinaryTree tree) {
        tree.printTree();
        System.out.println("Min:" + tree.findMin().key);
        System.out.println("Max:" + tree.findMax().key);
        System.out.println("------------");
        iterator = tree.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().key);
        }
        System.out.println("------------");
        System.out.println("------------");
    }

}
