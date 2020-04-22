package ifpb.gpes;

import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 06/07/2017, 19:07:01
 */
public interface ParseStrategy {

    public List<Call> from(Project project);
}
