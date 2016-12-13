
package BinarySearchTree;

import java.util.*;


class BinarySearchTree<K extends Comparable<K>, V extends Comparable<V>> extends BinaryTree<K, V> {

    protected void insert(K key, V value) {
        Node<K, V> newNode = new Node<K, V>(key, value);

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

    @Override
    protected Node<K, V> find(K key) {
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

    @Override
    protected Node<K, V> findMin() {
        Node<K, V> currentNode = root;
        Node<K, V> lastNode = root;
        while (currentNode != null) {
            lastNode = currentNode;
            currentNode = currentNode.leftChild;
        }
        return lastNode;
    }

    @Override
    protected Node<K, V> findMax() {
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

    @Override
    protected boolean delete(K key) {
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
}
