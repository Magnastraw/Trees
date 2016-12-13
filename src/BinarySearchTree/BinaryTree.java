package BinarySearchTree;


import java.util.*;

class BinaryTree<K extends Comparable<K>, V extends Comparable<V>> implements Iterable {
    protected Node<K, V> root;
    protected ArrayList<Node<K, V>> listTree = new ArrayList<>();
    private Node<K, V> findRoot;


    protected void insert(K key, V value, K keyRoot, String pos) {
        Node<K, V> newNode = new Node<K, V>();
        newNode.key = key;
        newNode.value = value;

        if (root == null) {
            this.root = newNode;
        } else {
            Node<K, V> parent = find(keyRoot);
            Node<K, V> currentNode;
            if (pos.equals("left")) {
                currentNode = parent.leftChild;
                parent.leftChild = newNode;
                newNode.leftChild = currentNode;
            } else if (pos.equals("right")) {
                currentNode = parent.rightChild;
                parent.rightChild = newNode;
                newNode.leftChild = currentNode;
            }
        }
    }

    protected boolean delete(K key) {
        Node<K, V> currentNode = root;
        Node<K, V> parentNode = root;
        Node<K, V> tempNode;
        boolean isLeftChild = true;


        Queue<Node<K, V>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            tempNode = queue.poll();
            if (tempNode.key.compareTo(key) == 0) {
                currentNode = tempNode;
                break;
            } else if (tempNode.leftChild.key.compareTo(key) == 0) {
                isLeftChild = true;
                parentNode = tempNode;
                currentNode = tempNode.leftChild;
                break;
            } else if (tempNode.rightChild.key.compareTo(key) == 0) {
                isLeftChild = false;
                parentNode = tempNode;
                currentNode = tempNode.rightChild;
                break;
            } else if (currentNode == null) {
                return false;
            }
            if (tempNode.leftChild != null) queue.add(tempNode.leftChild);
            if (tempNode.rightChild != null) queue.add(tempNode.rightChild);
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
            if (currentNode.compareTo(root) == 0) {
                root = currentNode.rightChild;
            } else if (isLeftChild) {
                parentNode.leftChild = currentNode.rightChild;
            } else {
                parentNode.rightChild = currentNode.rightChild;
            }
        }
        return true;
    }

    protected Node<K, V> find(K key) {
        find(root, key);
        return findRoot;
    }

    private void find(Node<K, V> root, K key) {
        if (root != null) {
            find(root.leftChild, key);
            if (root.key.compareTo(key) == 0) {
                findRoot = root;
                return;
            }
            find(root.rightChild, key);
        }
    }

    protected Node<K, V> findMin() {
        across(root);
        return Collections.min(listTree);
    }

    protected Node<K, V> findMax() {
        across(root);
        return Collections.max(listTree);
    }


    protected void inOrder(Node<K, V> root) {
        if (root != null) {
            inOrder(root.leftChild);

            System.out.println("Key=" + root.key + " Value=" + root.value);
            inOrder(root.rightChild);
        }
    }


    protected void preOrder(Node<K, V> root) {
        if (root != null) {
            System.out.println("Key=" + root.key + " Value=" + root.value);

            inOrder(root.leftChild);
            inOrder(root.rightChild);
        }
    }

    protected void postOrder(Node<K, V> root) {
        if (root != null) {
            inOrder(root.leftChild);
            inOrder(root.rightChild);

            System.out.println("Key=" + root.key + " Value=" + root.value);
        }
    }


    protected void across(Node<K, V> root) {
        Queue<Node<K, V>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            listTree.add(root);
            if (root.leftChild != null) queue.add(root.leftChild);
            if (root.rightChild != null) queue.add(root.rightChild);
        }
    }

    protected void printTree() {
        inOrder(this.root);
    }

    protected ArrayList<Node<K, V>> getList() {
        across(this.root);
        return listTree;
    }

    @Override
    public TreeIterator iterator() {
        return new TreeIterator(root);
    }
}
