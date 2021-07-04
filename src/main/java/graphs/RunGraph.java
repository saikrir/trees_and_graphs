package graphs;

public class RunGraph {
    public static void main(String[] args) {
        GraphAdjList graph = new GraphAdjList();
        Node sai = graph.addNode("Sai");
        Node jaya = graph.addNode("Jaya");
        Node mrinal = graph.addNode("Mrinal");
        Node shiva = graph.addNode("shiva");

        graph.addEdge(sai, jaya);
        graph.addEdge(sai, mrinal);
        graph.addEdge(jaya, mrinal);
        graph.addEdge(jaya, mrinal);
        graph.addEdge(jaya, shiva);
        graph.addEdge(mrinal, shiva);

        graph.printGraph();

        graph.removeNode(shiva);

        graph.printGraph();
    }
}
