package ifpb.gpes.jdt;

import ifpb.gpes.Call;
import ifpb.gpes.Parse;
import ifpb.gpes.Project;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class LambdaArgumentsTest {

    private static final Logger logger = Logger.getLogger(LambdaArgumentsTest.class.getName());
    private final List<Call> result = ofLambdaArguments();
    private static final String sources = "../mcp-samples/src/main/java/";

    @Test
    public void testeM1() {
        assertThat(result, hasItems(Call.of("java.lang.Iterable", "forEach[java.util.function.Consumer<? super ifpb.gpes.domain.HasJCFObject>]", "void", "ifpb.gpes.domain.LambdaWithArguments", "m1[]", "void", null, "new HasJCFObject().getElements()"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "getElements[]", "java.util.List<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.LambdaWithArguments", "m1[]", "void", "forEach[java.util.function.Consumer<? super ifpb.gpes.domain.HasJCFObject>]", "new HasJCFObject()"),
                Call.of("java.util.function.Predicate", "negate[]", "java.util.function.Predicate<ifpb.gpes.domain.HasJCFObject>", "java.lang.Object", "accept[ifpb.gpes.domain.HasJCFObject]", "void", null, "ts.m6(ts)"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "m6[ifpb.gpes.domain.HasJCFObject]", "java.util.function.Predicate<ifpb.gpes.domain.HasJCFObject>", "java.lang.Object", "accept[ifpb.gpes.domain.HasJCFObject]", "void", "negate[]", "ts"),
                Call.of("java.lang.Iterable", "forEach[java.util.function.Consumer<? super ifpb.gpes.domain.HasJCFObject>]", "void", "ifpb.gpes.domain.LambdaWithArguments", "m2[]", "void", null, "new HasJCFObject().getElements()"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "getElements[]", "java.util.List<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.LambdaWithArguments", "m2[]", "void", "forEach[java.util.function.Consumer<? super ifpb.gpes.domain.HasJCFObject>]", "new HasJCFObject()"),
                Call.of("java.util.function.Predicate", "negate[]", "java.util.function.Predicate<ifpb.gpes.domain.HasJCFObject>", "java.util.function.Consumer<ifpb.gpes.domain.HasJCFObject>", "accept[ifpb.gpes.domain.HasJCFObject]", "void", null, "t.m6(t)"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "m6[ifpb.gpes.domain.HasJCFObject]", "java.util.function.Predicate<ifpb.gpes.domain.HasJCFObject>", "java.util.function.Consumer<ifpb.gpes.domain.HasJCFObject>", "accept[ifpb.gpes.domain.HasJCFObject]", "void", "negate[]", "t"),
                Call.of("java.util.stream.Stream", "forEach[java.util.function.Consumer<? super ifpb.gpes.domain.HasJCFObject>]", "void", "ifpb.gpes.domain.LambdaWithArguments", "m3[]", "void", null, "a.getElements().stream()"),
                Call.of("java.util.Collection", "stream[]", "java.util.stream.Stream<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.LambdaWithArguments", "m3[]", "void", "forEach[java.util.function.Consumer<? super ifpb.gpes.domain.HasJCFObject>]", "a.getElements()")
        ));
        assertNotNull(result);
        assertFalse(result.isEmpty());
        result.forEach(no -> logger.log(Level.INFO, no.callGraph()));
    }

    private List<Call> ofLambdaArguments() {
        Project project = Project
                .root("")
                .path(sources + "ifpb/gpes/domain/LambdaWithArguments.java") // root
                .sources(sources) // root - não obrigatorio
                .filter(".java");

        return Parse.with(ParseStrategies.JDT).from(project);
    }

}
