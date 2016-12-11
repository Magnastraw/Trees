

package BinarySearchTree;


import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args){
        BinarySearchTree<Integer, Double> firstTree = new  BinarySearchTree<Integer, Double>();
        SelfBalanceTree<Integer, Double> secondTree = new SelfBalanceTree<Integer, Double>();
        firstTree.insert(47, 22.6);
        firstTree.insert(22, 16.3);
        firstTree.insert(50, 50.9);

        secondTree.insert(10, 20.0);
        secondTree.insert(15, 22.6);
        secondTree.insert(20, 26.3);
        secondTree.insert(25, 60.0);
        secondTree.insert(44, 55.0);


        TreeIterator iterator = secondTree.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().key);
        }
        ArrayList<Node> listTree = secondTree.getList();
        for(int i=0;i<listTree.size();i++){
            System.out.println(listTree.get(i).key);
        }

       /* System.out.println("Min:"+firstTree.findMin().key);
        System.out.println("Value of key 22 " + firstTree.find(22).value);*/
    }
    
}
