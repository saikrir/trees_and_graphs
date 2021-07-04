package graphs;

public interface IGraph<T extends Comparable<T>> {
    
    Node<T> addNode(T value);

    boolean addEdge(Node<T> a, Node<T> b);

    boolean removeNode(Node<T> node);
}
