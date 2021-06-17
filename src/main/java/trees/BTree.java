package trees;

import java.util.Objects;
import java.util.Optional;

public class BTree<T extends Comparable> implements ITree<T> {
    private T value;
    private BTree<T> parent;
    private BTree<T> left;
    private BTree<T> right;

    public BTree(T newValue, BTree<T> parent) {
        this.value = newValue;
        this.parent = parent;
        this.left = null;
        this.right = null;
    }

    public BTree<T> insertChild(T value) {

        if (!this.hasVacancyForNode()) {
            throw new UnsupportedOperationException("Tree is full");
        }

        BTree<T> childTree = new BTree<>(value, this);
        if (this.left == null) {
            this.left = childTree;
        } else {
            this.right = childTree;
        }
        return childTree;
    }

    public Optional<BTree<T>> removeChild(T value) {

        Optional<BTree<T>> treeOptional = findChild(value);
        if (treeOptional.isEmpty()) {
            return Optional.empty();
        }

        BTree<T> treeToDelete = treeOptional.get();

        BTree<T> parent = treeToDelete.parent;

        if (parent.left.equals(treeToDelete)) {
            parent.left = parent.right;
        }
        parent.right = null;

        return treeOptional;
    }

    public void traverse() {
        System.out.println("Value " + this.value);
        if (this.left != null) {
            this.left.traverse();
        }
        if (this.right != null) {
            this.right.traverse();
        }
    }

    public boolean contains(T value) {
        return findChild(value).isPresent();
    }

    public Optional<BTree<T>> findChild(T value) {
        if (this.value.compareTo(value) == 0) {
            return Optional.of(this);
        }
        Optional<BTree<T>> retVal = Optional.empty();
        if (this.left != null) {
            if (this.left.value.compareTo(value) == 0) {
                retVal = Optional.of(this.left);
            } else {
                retVal = this.left.findChild(value);
            }
        }
        if (retVal.isEmpty() && this.right != null) {
            if (this.right.value.compareTo(value) == 0) {
                retVal = Optional.of(this.right);
            } else {
                retVal = this.right.findChild(value);
            }
        }
        return retVal;
    }

    public int leafCount() {
        if (this.isLeafNode()) return 1;
        int leftCount = 0;
        int rightCount = 0;
        if (this.left != null) {
            leftCount = this.left.isLeafNode() ? 1 : this.left.leafCount();
        }
        if (this.right != null) {
            rightCount = this.right.isLeafNode() ? 1 : this.right.leafCount();
        }

        return leftCount + rightCount;
    }

    public boolean hasVacancyForNode() {
        return (this.left == null || this.right == null);
    }

    public boolean isLeafNode() {
        return (this.left == null && this.right == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BTree<?> bTree = (BTree<?>) o;
        return value.equals(bTree.value) && left.equals(bTree.left) && right.equals(bTree.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, left, right);
    }

    @Override
    public String toString() {
        return "BTree{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
