package BinarySearchTree;


class SelfBalanceTree<K extends Comparable<K>, V extends Comparable<V>> extends BinarySearchTree<K, V> {

    private int max(int lhs, int rhs) {
        return lhs > rhs ? lhs : rhs;
    }

    private int height(Node<K, V> root) {
        return root == null ? -1 : root.height;
    }

    @Override
    public void insert(K key, V value) {
        root = insert(key, value, root);
    }

    private Node<K, V> insert(K key, V value, Node<K, V> subRoot) {
        Node<K, V> newNode = new Node<K, V>();
        newNode.key = key;
        newNode.value = value;

        if (subRoot == null) {
            subRoot = newNode;
        } else if (key.compareTo(subRoot.key) < 0) {
            subRoot.leftChild = insert(key, value, subRoot.leftChild);
            if (height(subRoot.leftChild) - height(subRoot.rightChild) == 2) {
                if (key.compareTo(subRoot.leftChild.key) < 0) {
                    subRoot = rotateWithLeftChild(subRoot);
                } else {
                    subRoot = doubleWithRightChild(subRoot);
                }
            }
        } else if (key.compareTo(subRoot.key) > 0) {
            subRoot.rightChild = insert(key, value, subRoot.rightChild);
            if (height(subRoot.rightChild) - height(subRoot.leftChild) == 2) {
                if (key.compareTo(subRoot.rightChild.key) > 0) {
                    subRoot = rotateWithRightChild(subRoot);
                } else {
                    subRoot = doubleWithLeftChild(subRoot);
                }
            }
        }
        subRoot.height = max(height(subRoot.leftChild), height(subRoot.rightChild)) + 1;

        return subRoot;
    }

    private Node<K, V> rotateWithLeftChild(Node<K, V> root) {
        Node<K, V> subRoot = root.leftChild;
        root.leftChild = subRoot.rightChild;
        subRoot.rightChild = root;
        root.height = max(height(root.leftChild), height(root.rightChild)) + 1;
        subRoot.height = max(height(subRoot.leftChild), subRoot.height) + 1;
        return subRoot;
    }

    private Node<K, V> rotateWithRightChild(Node<K, V> root) {
        Node<K, V> subRoot = root.rightChild;
        root.rightChild = subRoot.leftChild;
        subRoot.leftChild = root;
        root.height = max(height(root.leftChild), height(root.rightChild)) + 1;
        subRoot.height = max(height(subRoot.rightChild), root.height) + 1;
        return subRoot;

    }

    private Node<K, V> doubleWithLeftChild(Node<K, V> root) {
        root.leftChild = rotateWithRightChild(root.leftChild);
        return rotateWithLeftChild(root);
    }

    private Node<K, V> doubleWithRightChild(Node<K, V> root) {
        root.rightChild = rotateWithLeftChild(root.rightChild);
        return rotateWithRightChild(root.rightChild);
    }


    private Node delete(K key, Node<K, V> node) {
      /*  if (node == null) return null;
        int compareResult = key.compareTo(node.key);
        if (compareResult > 0) {
            node.rightChild = delete(key, node.rightChild);
        } else if (compareResult < 0) {
            node.leftChild = delete(key, node.leftChild);
        } else {
            if (node.rightChild == null && node.leftChild == null) {
                node = null;
            } else if (node.rightChild == null) {
                node.leftChild.father = node.father;
                node = node.left;
            } else if (node.left == null) {
                node.right.father = node.father;
                node = node.right;
            } else {
                if (node.right.left == null) {
                    node.right.left = node.left;
                    node.right.father = node.father;
                    node.right.father = node.father;
                    node.left.father = node.right;
                    node = node.right;
                } else {
                    Node res = min(node.right);
                    node.key = res.key;
                    node.value = res.value;
                    delete(node.right, node.key);
                }
            }
        }
        if (node != null) {
            node.h = height(node.left, node.right) + 1;
            node.balance = balance(node.left, node.right);
            if (node.balance == -2) {
                node = leftRotation(node);
            } else if (node.balance == 2) {
                node = rightRotation(node);
            }
        }*/
        return node;
    }

    @Override
    public boolean delete(K key) {
        root = delete(key, root);
        return true;
    }
}


