package trees;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

public class Tree<T extends Comparable> implements ITree<T> {

    private T value;
    private Tree<T> parent;
    private ArrayList<Tree<T>> children;

    public Tree(T someValue, Tree<T> parent) {
        this.value = someValue;
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    @Override
    public Tree<T> insertChild(T someValue) {
        Tree<T> childTree = new Tree<>(someValue, this);
        children.add(childTree);
        return childTree;
    }

    @Override
    public Optional<Tree<T>> findChild(T someValue) {

        if (someValue.compareTo(value) == 0) {
            return Optional.of(this);
        }

        for (Tree<T> child : children) {
            Optional<Tree<T>> foundValueOptional = child.findChild(someValue);
            if (foundValueOptional.isPresent()) {
                return foundValueOptional;
            }
        }

        return Optional.empty();
    }

    @Override
    public boolean isLeafNode() {
        return this.children.isEmpty();
    }

    @Override
    public int leafCount() {
        if (this.isLeafNode()) return 1;

        int leafCount = 0;

        for (ITree<T> child : this.children) {
            leafCount += child.leafCount();
        }
        return leafCount;
    }

    @Override
    public Optional<Tree<T>> removeChild(T someValue) {
        Optional<Tree<T>> childToDelete
                = this.findChild(someValue);

        if (childToDelete.isPresent()) {
            Tree<T> nodeToDelete = childToDelete.get();
            Tree<T> parent = nodeToDelete.parent;
            if (parent != null) {
                for (Tree<T> child : nodeToDelete.children) {
                    parent.insertChild(child.value);
                }
                parent.children.remove(nodeToDelete);
            }
        }
        return childToDelete;
    }

    @Override
    public void traverse() {
        System.out.println(" Value -> " + this.value + " \n " + this.children);
        for (ITree<T> child : this.children) {
            child.traverse();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tree<?> tree = (Tree<?>) o;
        return value.equals(tree.value) && children.equals(tree.children);
    }

    @Override
    public boolean contains(T value) {
        return findChild(value).isPresent();
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, children);
    }

    @Override
    public String toString() {
        return "{ value = " + value + "}";
    }
}
