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
        Node<K, V> newNode = new Node<K, V>(key, value);

        if (subRoot == null) {
            subRoot = newNode;
        } else if (key.compareTo(subRoot.key) < 0) {
            subRoot.leftChild = insert(key, value, subRoot.leftChild);
            if (height(subRoot.leftChild) - height(subRoot.rightChild) == 2) {
                if (key.compareTo(subRoot.leftChild.key) < 0) {
                    subRoot = rotateWithLeftChild(subRoot);
                } else {
                    subRoot = doubleWithLeftChild(subRoot);
                }
            }
        } else if (key.compareTo(subRoot.key) > 0) {
            subRoot.rightChild = insert(key, value, subRoot.rightChild);
            if (height(subRoot.rightChild) - height(subRoot.leftChild) == 2) {
                if (key.compareTo(subRoot.rightChild.key) > 0) {
                    subRoot = rotateWithRightChild(subRoot);
                } else {
                    subRoot = doubleWithRightChild(subRoot);
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
        subRoot.height = max(height(subRoot.leftChild), root.height) + 1;
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
        return rotateWithRightChild(root);
    }

    @Override
    protected boolean delete(K key) {
        root = remove(key, root);
        return true;
    }

    private Node<K, V> remove(K key, Node<K, V> node) {

        if (key.compareTo(node.key) < 0) {
            node.leftChild = remove(key, node.leftChild);
            int l = node.leftChild != null ? node.leftChild.height : 0;

            if ((node.rightChild != null) && (node.rightChild.height - l >= 2)) {
                int rightHeight = node.rightChild.rightChild != null ? node.rightChild.rightChild.height : 0;
                int leftHeight = node.rightChild.leftChild != null ? node.rightChild.leftChild.height : 0;

                if (rightHeight >= leftHeight)
                    node = rotateWithLeftChild(node);
                else
                    node = doubleWithRightChild(node);
            }
        } else if (key.compareTo(node.key) > 0) {
            node.rightChild = remove(key, node.rightChild);
            int r = node.rightChild != null ? node.rightChild.height : 0;
            if ((node.leftChild != null) && (node.leftChild.height - r >= 2)) {
                int leftHeight = node.leftChild.leftChild != null ? node.leftChild.leftChild.height : 0;
                int rightHeight = node.leftChild.rightChild != null ? node.leftChild.rightChild.height : 0;
                if (leftHeight >= rightHeight)
                    node = rotateWithRightChild(node);
                else
                    node = doubleWithLeftChild(node);
            }
        } else if (node.leftChild != null) {
            node.key = findMax(node.leftChild).key;
            remove(node.key, node.leftChild);

            if ((node.rightChild != null) && (node.rightChild.height - node.leftChild.height >= 2)) {
                int rightHeight = node.rightChild.rightChild != null ? node.rightChild.rightChild.height : 0;
                int leftHeight = node.rightChild.leftChild != null ? node.rightChild.leftChild.height : 0;

                if (rightHeight >= leftHeight)
                    node = rotateWithLeftChild(node);
                else
                    node = doubleWithRightChild(node);
            }
        } else {
            node = (node.leftChild != null) ? node.leftChild : node.rightChild;
        }

        if (node != null) {
            int leftHeight = node.leftChild != null ? node.leftChild.height : 0;
            int rightHeight = node.rightChild != null ? node.rightChild.height : 0;
            node.height = Math.max(leftHeight, rightHeight) + 1;
        }
        return node;
    }
}


