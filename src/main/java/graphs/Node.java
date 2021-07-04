package graphs;

import lombok.Data;

@Data
public class Node<T extends Comparable<T>> {
    private T value;

    public Node(T value) {
        this.value = value;
    }
}
