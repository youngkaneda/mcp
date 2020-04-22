package ifpb.gpes.graph;

import java.util.function.Supplier;
import org.jgrapht.graph.AbstractBaseGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 20/11/2017, 16:51:04
 */
public class AdapterMatrix implements Supplier<Matrix> {

    private Matrix matrix = new Matrix();
    private final AbstractBaseGraph<Node, DefaultWeightedEdge> graph;

    public AdapterMatrix(AbstractBaseGraph<Node, DefaultWeightedEdge> graph) {
        this.graph = graph;
    }

    @Override
    public Matrix get() {
        Node[] vertices = graph.vertexSet().toArray(new Node[]{});
        int numeroDeVertices = vertices.length;

        this.matrix = new Matrix(numeroDeVertices);

        for (int i = 0; i < numeroDeVertices; i++) {
            for (int j = 0; j < numeroDeVertices; j++) {
                Matrix.Cell cell = matrix.cell(i, j);
                DefaultWeightedEdge edge = edge(vertices, i, j);
                cell.set(weight(edge));
            }
            matrix.updateColumns(i, vertices[i]);
        }
        return this.matrix;
    }

    private DefaultWeightedEdge edge(Node[] vertices, int i, int j) {
        Node firstNode = vertices[i];
        Node secondNode = vertices[j];
        DefaultWeightedEdge edge = graph.getEdge(firstNode, secondNode);
        return edge;
    }

    private int weight(DefaultWeightedEdge edge) {
        if (edge != null) {
            return (int) graph.getEdgeWeight(edge);
        } else {
            return 0;
        }
    }

}
