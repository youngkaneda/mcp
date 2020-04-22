package ifpb.gpes.jdt;


import ifpb.gpes.Call;
import ifpb.gpes.ParseStrategy;
import ifpb.gpes.Project;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 06/07/2017, 19:07:59
 */
public enum ParseStrategies implements ParseStrategy {

    JDT {
        @Override
        public List<Call> from(Project project) {
            return new SmartParseJDT().from(project);
        }
    }
}
