package BinarySearchTree;



import java.util.Iterator;
import java.util.Stack;


class TreeIterator implements Iterator {
    private Stack<Node> stack;
    private Node resultNode;

    public TreeIterator(Node root) {
        stack = new Stack<Node>();
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
    public Node next() {
        this.resultNode = stack.pop();
        Node node = resultNode;
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
