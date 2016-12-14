package BinarySearchTree;



import java.util.Iterator;
import java.util.Stack;


class TreeIterator<K extends Comparable<K>, V extends Comparable<V>> implements Iterator {
    private Stack<Node<K, V>> stack;
    protected Node<K, V> resultNode;

    TreeIterator(Node<K, V> root) {
        stack = new Stack<Node<K, V>>();
        while (root != null) {
            stack.push(root);
            root = root.leftChild;
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Node<K, V> next() {
        this.resultNode = stack.pop();
        Node<K, V> node = resultNode;
        if (node.rightChild != null) {
            node = node.rightChild;
            while (node != null) {
                stack.push(node);
                node = node.leftChild;
            }
        }
        return this.resultNode;
    }

    @Override
    public void remove() {
    }
}
