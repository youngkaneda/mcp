package ifpb.gpes.graph;

import ifpb.gpes.Call;
import java.util.List;
import java.util.function.Function;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 19/11/2017, 18:23:21
 */
public class AdapterGraph implements Function<List<Call>, Graph> {

    @Override
    public Graph apply(List<Call> calls) {
        Graph graph = calls.stream()
                .collect(DefaultDirectGraph::new,
                        DefaultDirectGraph::buildNode,
                        (t, u) -> {
                        });
        return graph;
    }

}
