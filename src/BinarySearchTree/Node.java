package BinarySearchTree;




class Node<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Node<K, V>> {
    K key;
    V value;
    int height;

    Node<K, V> leftChild;
    Node<K, V> rightChild;

    Node (K key, V value){
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(Node<K, V> o) {
        return this.key.compareTo(o.key);
    }
}
