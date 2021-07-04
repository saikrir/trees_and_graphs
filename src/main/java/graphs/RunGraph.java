package graphs;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Slf4j
public class RunGraph {
    public static void main(String[] args) {

        String nodeToStart = "Sai";
        GraphAdjList<String> graph = sample1();

        Optional<Node<String>> optional = graph.findNode(nodeToStart);
        if(optional.isEmpty()){
            System.out.println("No Nodes yet");
        }else{
            System.out.println("BFS Starting " +optional.get() );
            long start = System.currentTimeMillis();
            System.out.println("Are Related ? " + graph.find(optional.get(), graph.findNode("Ishita").get() ));

            log.info("Finished in {} ms ", System.currentTimeMillis() - start);
        }

    }

    protected static GraphAdjList<String> sample1() {

        GraphAdjList<String> graph = new GraphAdjList<>();
        Node sai = graph.addNode("Sai");
        Node jaya = graph.addNode("Jaya");
        Node mrinal = graph.addNode("Mrinal");
        Node shiva = graph.addNode("Shiva");
        Node ishita = graph.addNode("Ishita");

        graph.addEdge(sai, jaya);
        graph.addEdge(sai, mrinal);
        graph.addEdge(jaya, mrinal);
        graph.addEdge(jaya, mrinal);
        graph.addEdge(jaya, shiva);
        graph.addEdge(mrinal, shiva);

        return graph;
    }


    protected static GraphAdjList<String> sample2() {

        GraphAdjList<String> graph = new GraphAdjList<>();
        Node one = graph.addNode(String.valueOf(1));
        Node two = graph.addNode(String.valueOf(2));
        Node three = graph.addNode(String.valueOf(3));
        Node four = graph.addNode(String.valueOf(4));
        Node five = graph.addNode(String.valueOf(5));
        Node six = graph.addNode(String.valueOf(6));
        Node seven = graph.addNode(String.valueOf(7));
        Node eight = graph.addNode(String.valueOf(8));
        Node nine = graph.addNode(String.valueOf(9));
        Node ten = graph.addNode(String.valueOf(10));
        Node eleven = graph.addNode(String.valueOf(11));


        graph.addEdge(one, two);
        graph.addEdge(one, three);
        graph.addEdge(one, four);
        graph.addEdge(two, five);
        graph.addEdge(five, nine);
        graph.addEdge(five, eleven);
        graph.addEdge(three, six);
        graph.addEdge(three, seven);
        graph.addEdge(six, ten);
        graph.addEdge(four, eight);

        return graph;
    }
}
