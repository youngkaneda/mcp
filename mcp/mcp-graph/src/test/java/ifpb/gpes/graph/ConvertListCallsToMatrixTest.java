package ifpb.gpes.graph;

import ifpb.gpes.Call;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 19/11/2017, 16:11:02
 */
public class ConvertListCallsToMatrixTest {

    private List<Call> calls;

    @Before
    public void before() throws IOException {
        this.calls = Arrays.asList(
                Call.of("java.util.List", "add[ifpb.gpes.domain.HasJCFObject]", "boolean", "ifpb.gpes.domain.SampleObject", "m2[]", "void", null, "a.getElements()"),
                Call.of("java.util.List", "remove[java.lang.Object]", "boolean", "ifpb.gpes.domain.SampleObject", "m3[]", "void", null, "list"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "getElements[]", "java.util.List<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.SampleObject", "m3[]", "void", "isEmpty[]", "a"));
    }

    @Test
    public void collectTest() throws IOException {

        Graph graph = calls.stream().collect(DefaultDirectGraph::new,
                DefaultDirectGraph::buildNode, (DefaultDirectGraph t, DefaultDirectGraph u) -> {
                });

        Matrix generateMatrix = graph.toMatrix();

        assertNotNull(graph);
        assertNotNull(generateMatrix);
        assertEquals(generateMatrix.sumAllWeight(), 2);
    }

    @Test
    public void reduceTest() throws IOException {

        Graph graph = calls.stream().reduce(new DefaultDirectGraph(),
                (DefaultDirectGraph t, Call u) -> {
                    t.buildNode(u);
                    return t;
                }, (DefaultDirectGraph t, DefaultDirectGraph u) -> t);

        Matrix generateMatrix = graph.toMatrix();

        assertNotNull(graph);
        assertNotNull(generateMatrix);
        assertEquals(generateMatrix.sumAllWeight(), 2);
    }
    @Test
    public void adapterTest() throws IOException {

        Graph graph = new AdapterGraph()
                .apply(calls);

        Matrix generateMatrix = graph.toMatrix();

        assertNotNull(graph);
        assertNotNull(generateMatrix);
        assertEquals(generateMatrix.sumAllWeight(), 2);
    }

}
