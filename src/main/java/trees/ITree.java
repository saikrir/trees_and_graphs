package trees;

import java.util.Optional;

public interface ITree<T extends Comparable> {
    ITree<T> insertChild(T someValue);

    Optional<? extends ITree<T>> findChild(T someValue);

    boolean isLeafNode();

    int leafCount();

    Optional<? extends ITree<T>> removeChild(T someValue);

    void traverse();

    boolean contains(T value);
}
