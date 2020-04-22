package ifpb.gpes.domain;

import java.util.List;
import java.util.function.Predicate;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 19/08/2016, 17:34:16
 */
public class HasJCFObject {

    private List<HasJCFObject> elements;

    public List<HasJCFObject> getElements() {
        return this.elements;
    }

    public Predicate<HasJCFObject> m6(HasJCFObject a) {

        return new Predicate<HasJCFObject>() {
            @Override
            public boolean test(HasJCFObject t) {
                return false;
            }
        };
    }
}
