package trees;

public class RunTree {
    public static void main(String[] args) {
        Runnable r1 = () -> {
            Tree<Integer> tree = new Tree<>(10, null);
            Tree<Integer> childTree1 = tree.insertChild(20);
            childTree1.insertChild(10);
            Tree<Integer> l3 = childTree1.insertChild(2);
            l3.insertChild(2);
            l3.insertChild(1);
            Tree<Integer> childTree2 = tree.insertChild(40);
            childTree2.insertChild(5);
            Tree<Integer> j3 = childTree2.insertChild(2);
            j3.insertChild(0);
            j3.insertChild(2);
            childTree2.insertChild(4);
            //tree.traverse();
            //tree.removeChild(40);
            System.out.println("After ");
            tree.traverse();
            System.out.println("Count " +tree.leafCount());
        };

        Runnable r2 = () -> {
            BTree<Integer> tree = new BTree<>(1, null);
            BTree<Integer> childTree1 = tree.insertChild(2);
            BTree<Integer> gChild = childTree1.insertChild(4);
            childTree1.insertChild(5);
            gChild.insertChild(8);
            gChild.insertChild(9);

            BTree<Integer> childTree2 = tree.insertChild(3);
            childTree2.insertChild(6);
            BTree<Integer> gchild1 = childTree2.insertChild(7);
            //gchild1.insertChild(10);
            tree.traverse();
            System.out.println("Contains 10 ?" + tree.findChild(10));
            System.out.println("Leaf Count " + tree.leafCount());
        };
        r1.run();
    }
}
