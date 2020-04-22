package ifpb.gpes.filter;

import ifpb.gpes.Call;

import java.util.function.Predicate;

/**
 *
 * @author juan
 */
public class FilterClassType implements Predicate<Call> {

    private AssignVerifier verifier;

    public FilterClassType(String type) {
        verifier = new AssignVerifier(type);
    }

    @Override
    public boolean test(Call t) {
        if (t.getClassType() == null || !t.getClassType().contains("java.util.")) {
            return false;
        }
        return  verifier.isAssignable(t.getClassType().split("<")[0]);
    }
}
