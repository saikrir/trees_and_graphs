package graphs;

import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class GraphAdjList<T extends Comparable<T>> implements IGraph<T> {


    private final List<Node<T>> visitedNodes;
    private final Stack<Node<T>> traversalStack;
    private final Queue<Node<T>> unexploredQueue;
    private final Map<Node<T>, List<Node<T>>> graphStore;

    public GraphAdjList() {
        this.graphStore = new HashMap<>();
        this.visitedNodes = new ArrayList<>();
        this.traversalStack = new Stack<>();
        this.unexploredQueue = new ArrayDeque<>();
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

    public void performDFSR(Node<T> aNode) {

        if (visitedNodes.contains(aNode)) {
            return;
        }

        log.info("Visiting {}", aNode.getValue());
        this.visitedNodes.add(aNode);
        this.traversalStack.push(aNode);

        List<Node<T>> children = this.graphStore.get(aNode);
        if (children.isEmpty()) {
            this.traversalStack.pop();
        } else {
            for (Node<T> childNode : children) {
                performDFS(childNode);
            }
        }
    }

    public void performDFS(Node<T> aNode) {
        traversalStack.push(aNode);
        while (!traversalStack.isEmpty()) {
            Node<T> someNode = traversalStack.pop();
            if (visitedNodes.contains(someNode)) continue;
            visitedNodes.add(someNode);
            log.info("Visited {} ", someNode.getValue());
            this.graphStore.get(someNode).stream().filter(tNode -> !visitedNodes.contains(tNode)).forEach(node -> traversalStack.push(node));
        }
    }


    public boolean find(Node<T> fNode, Node<T> toNode) {
        traversalStack.push(fNode);
        while (!traversalStack.isEmpty()) {
            Node<T> someNode = traversalStack.pop();
            if (visitedNodes.contains(someNode)) continue;
            visitedNodes.add(someNode);
            if(someNode.getValue().compareTo(toNode.getValue()) == 0) {
                return true;
            }else {
                this.graphStore.get(someNode).stream().filter(tNode -> !visitedNodes.contains(tNode)).forEach(node -> traversalStack.push(node));
            }
        }
        return false;
    }

    public void performBFS(Node<T> aNode) {
        unexploredQueue.add(aNode);
        while (unexploredQueue.peek() != null) {
            Node<T> someNode = unexploredQueue.remove();
            if (visitedNodes.contains(someNode)) continue;
            visitedNodes.add(someNode);
            log.info("Visited {} ", someNode.getValue());
            List<Node<T>> adjacentList = this.graphStore.get(someNode);
            adjacentList.forEach(tNode -> unexploredQueue.add(tNode));
        }
    }

    public Optional<Node<T>> findNode(T value) {
        return this.graphStore.keySet().stream().filter(node -> node.getValue().compareTo(value) == 0).findAny();
    }

    public void printGraph() {
        log.info("----------------- Printing Graph -----------------");
        this.graphStore.forEach((tNode, nodes) -> {
            String joinedNodes = nodes.stream().map(connectedNode -> connectedNode.getValue().toString()).collect(Collectors.joining(","));
            log.info("Node {} is connected to [ {} ]", tNode.toString(), joinedNodes);
        });
    }

    public Optional<Node<T>> firstNode() {
        return this.graphStore.keySet().stream().findFirst();
    }

}
