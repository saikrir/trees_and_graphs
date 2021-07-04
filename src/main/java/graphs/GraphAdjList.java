package graphs;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class GraphAdjList<T extends Comparable<T>> implements IGraph<T> {


    private final Map<Node<T>, List<Node<T>>> graphStore;

    public GraphAdjList() {
        this.graphStore = new HashMap<>();
    }

    @Override
    public Node<T> addNode(T value) {
        Optional<Node<T>> optionalNode = findNode(value);
        if (optionalNode.isPresent()) {
            optionalNode.get();
        }

        Node<T> node = new Node<T>(value);
        graphStore.put(node, new LinkedList<>());
        return node;
    }

    @Override
    public boolean addEdge(Node<T> a, Node<T> b) {
        if (this.graphStore.containsKey(a) && this.graphStore.containsKey(b)) {
            if (!this.graphStore.get(a).contains(b)) {
                this.graphStore.get(a).add(b);
            }
            if (!this.graphStore.get(b).contains(a)) {
                this.graphStore.get(b).add(a);
            }
            return true;
        }

        return false;
    }

    @Override
    public boolean removeNode(Node<T> node) {

        if (this.graphStore.containsKey(node)) {
            this.graphStore.remove(node);

            Iterator<List<Node<T>>> iterator = this.graphStore.values().iterator();
            while (iterator.hasNext()) {
                LinkedList<Node<T>> adjList = (LinkedList<Node<T>>) iterator.next();
                adjList.remove(node);
            }
            return true;
        }
        return false;
    }

    private Optional<Node<T>> findNode(T value) {
        return this.graphStore.keySet().stream().filter(node -> node.getValue().compareTo(value) == 0).findAny();
    }

    public void printGraph() {
        log.info("----------------- Printing Graph -----------------");
        this.graphStore.forEach((tNode, nodes) -> {
            String joinedNodes = nodes.stream().map(connectedNode -> connectedNode.getValue().toString()).collect(Collectors.joining(","));
            log.info("Node {} is connected to [ {} ]", tNode.toString(), joinedNodes);
        });
    }

}
