package ifpb.gpes;

import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 05/07/2017, 15:28:08
 */
public class Parse {

    private final ParseStrategy type;

    private Parse(ParseStrategy type) {
        this.type = type;
    }

    public static Parse with(ParseStrategy type) {
        return new Parse(type);
    }

    public List<Call> from(Project project) {
        return type.from(project);
    }
}
