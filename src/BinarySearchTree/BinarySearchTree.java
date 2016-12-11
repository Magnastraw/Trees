
package BinarySearchTree;

import java.util.*;


public class BinarySearchTree<K extends Comparable<K>, V extends Comparable<V>> implements Iterable {
    protected Node<K, V> root;
    protected ArrayList<Node> listTree = new ArrayList<>();

    public void insert(K key, V value) {
        Node<K, V> newNode = new Node<K, V>();
        newNode.key = key;
        newNode.value = value;

        if (root == null) {
            root = newNode;
        } else {
            Node<K, V> currentNode = this.root;
            Node<K, V> parent;
            while (true) {
                parent = currentNode;
                if (key.compareTo(currentNode.key) < 0) {
                    currentNode = currentNode.leftChild;

                    if (currentNode == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    currentNode = currentNode.rightChild;

                    if (currentNode == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }

    public Node<K, V> find(K key) {
        Node<K, V> currentNode = root;

        while (currentNode.key.compareTo(key) != 0) {
            if (key.compareTo(currentNode.key) < 0) {
                currentNode = currentNode.leftChild;
            } else {
                currentNode = currentNode.rightChild;
            }
            if (currentNode == null) {
                return null;
            }
        }
        return currentNode;
    }

    public Node<K, V> findMin() {
        Node<K, V> currentNode = root;
        Node<K, V> lastNode = root;
        while (currentNode != null) {
            lastNode = currentNode;
            currentNode = currentNode.leftChild;
        }
        return lastNode;
    }

    public Node<K, V> findMax() {
        Node<K, V> currentNode = root;
        Node<K, V> lastNode = root;
        while (currentNode != null) {
            lastNode = currentNode;
            currentNode = currentNode.rightChild;
        }
        return lastNode;
    }

    private Node<K, V> getSuccessor(Node<K, V> delNode){
        Node<K, V> successorParent = delNode;
        Node<K, V> successor = delNode;
        Node<K, V> current = delNode.rightChild;
        while(current!=null){
            successorParent = successor;
            successor = current;
            current = current.rightChild;
        }

        if(successor.compareTo(delNode.rightChild)!=0){
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }
        return successor;
    }

    public boolean delete(K key) {
        Node<K, V> currentNode = root;
        Node<K, V> parentNode = root;
        boolean isLeftChild = true;

        while (currentNode.key.compareTo(key) != 0) {
            parentNode = currentNode;
            if (key.compareTo(currentNode.key) < 0) {
                isLeftChild = true;
                currentNode = currentNode.leftChild;
            } else {
                isLeftChild = false;
                currentNode = currentNode.rightChild;
            }
            if (currentNode == null) {
                return false;
            }
        }

        if (currentNode.leftChild == null && currentNode.rightChild == null) {
            if (currentNode.compareTo(root) == 0) {
                root = null;
            } else if (isLeftChild) {
                parentNode.leftChild = null;
            } else {
                parentNode.rightChild = null;
            }
        } else if (currentNode.rightChild == null) {
            if (currentNode.compareTo(root) == 0) {
                root = currentNode.leftChild;
            } else if (isLeftChild) {
                parentNode.leftChild = currentNode.leftChild;
            } else {
                parentNode.rightChild = currentNode.leftChild;
            }
        } else if (currentNode.leftChild == null) {
            if(currentNode.compareTo(root)==0){
                root = currentNode.rightChild;
            } else if(isLeftChild){
                parentNode.leftChild = currentNode.rightChild;
            } else {
                parentNode.rightChild = currentNode.rightChild;
            }
        } else {
            Node<K, V> successor = getSuccessor(currentNode);

            if(currentNode.compareTo(root)==0){
                root = successor;
            } else if(isLeftChild){
                parentNode.leftChild = successor;
            } else {
                parentNode.rightChild = successor;
            }
            successor.leftChild = currentNode.leftChild;
        }
        return true;
    }

    private void inOrder(Node<K, V> root) {
        if (root != null) {
            inOrder(root.leftChild);

            System.out.println("Key=" + root.key + " Value=" + root.value);
            inOrder(root.rightChild);
        }
    }

    private void preOrder(Node<K, V> root){
        if (root != null) {
            System.out.println("Key=" + root.key + " Value=" + root.value);

            inOrder(root.leftChild);
            inOrder(root.rightChild);
        }
    }

    private void postOrder(Node<K, V> root){
        if (root != null) {
            inOrder(root.leftChild);
            inOrder(root.rightChild);

            System.out.println("Key=" + root.key + " Value=" + root.value);
        }
    }


    private void across(Node<K, V> root){
        Queue<Node<K, V>> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            root = queue.poll();
            listTree.add(root);
            if (root.leftChild!=null) queue.add(root.leftChild);
            if (root.rightChild!=null) queue.add(root.rightChild);
        }
    }

    public ArrayList<Node> getList() {
        across(this.root);
        return listTree;
    }

    @Override
    public TreeIterator iterator() {
        return new TreeIterator(root);
    }

}
