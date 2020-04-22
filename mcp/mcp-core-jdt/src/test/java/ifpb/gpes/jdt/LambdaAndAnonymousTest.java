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

public class LambdaAndAnonymousTest {

    private static final Logger logger = Logger.getLogger(LambdaAndAnonymousTest.class.getName());
    private final List<Call> result = ofLambdaArguments();
    private static final String sources = "../mcp-samples/src/main/java/";

    @Test
    public void testeM1() {
        assertThat(result, hasItems(
                Call.of("ifpb.gpes.domain.SampleObject", "teste[]", "void", "ifpb.gpes.domain.LambdaAndAnonymous", "testeOutro[]", "void", null, "this.d"),
                Call.of("ifpb.gpes.domain.Interface", "semRetorno[]", "void", "ifpb.gpes.domain.LambdaAndAnonymous", "m3[]", "void", null, "i"),
                Call.of("java.util.List", "iterator[]", "java.util.Iterator<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.Interface", "semRetorno[]", "void", null, "new HasJCFObject().getElements()"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "getElements[]", "java.util.List<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.Interface", "semRetorno[]", "void", "iterator[]", "new HasJCFObject()"),
                Call.of("java.util.stream.Stream", "forEach[java.util.function.Consumer<? super ifpb.gpes.domain.HasJCFObject>]", "void", "ifpb.gpes.domain.AbstractClass", "m5[]", "java.lang.String", null, "new HasJCFObject().getElements().stream()"),
                Call.of("java.util.Collection", "stream[]", "java.util.stream.Stream<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.AbstractClass", "m5[]", "java.lang.String", "forEach[java.util.function.Consumer<? super ifpb.gpes.domain.HasJCFObject>]", "new HasJCFObject().getElements()"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "getElements[]", "java.util.List<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.AbstractClass", "m5[]", "java.lang.String", "stream[]", "new HasJCFObject()"),
                Call.of("java.util.function.Predicate", "negate[]", "java.util.function.Predicate<ifpb.gpes.domain.HasJCFObject>", "java.util.function.Consumer<ifpb.gpes.domain.HasJCFObject>", "accept[ifpb.gpes.domain.HasJCFObject]", "void", null, "ts.m6(ts)"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "m6[ifpb.gpes.domain.HasJCFObject]", "java.util.function.Predicate<ifpb.gpes.domain.HasJCFObject>", "java.util.function.Consumer<ifpb.gpes.domain.HasJCFObject>", "accept[ifpb.gpes.domain.HasJCFObject]", "void", "negate[]", "ts"),
                Call.of("java.lang.Object", "hashCode[]", "int", "java.util.function.Consumer<ifpb.gpes.domain.HasJCFObject>", "accept[ifpb.gpes.domain.HasJCFObject]", "void", null, "ts.m6(ts)"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "m6[ifpb.gpes.domain.HasJCFObject]", "java.util.function.Predicate<ifpb.gpes.domain.HasJCFObject>", "java.util.function.Consumer<ifpb.gpes.domain.HasJCFObject>", "accept[ifpb.gpes.domain.HasJCFObject]", "void", "hashCode[]", "ts"),
                Call.of("ifpb.gpes.domain.LambdaAndAnonymous", "listar[java.util.List<ifpb.gpes.domain.HasJCFObject>]", "void", "ifpb.gpes.domain.LambdaAndAnonymous", "m3[]", "void", null, "this"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "getElements[]", "java.util.List<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.LambdaAndAnonymous", "m3[]", "void", null, "a"),
                Call.of("java.util.List", "get[int]", "ifpb.gpes.domain.HasJCFObject", "ifpb.gpes.domain.LambdaAndAnonymous", "listar[java.util.List<ifpb.gpes.domain.HasJCFObject>]", "void", null, "lista")
        ));
        assertNotNull(result);
        assertFalse(result.isEmpty());
        result.forEach(no -> logger.log(Level.INFO, no.callGraph()));
    }

    private List<Call> ofLambdaArguments() {
        Project project = Project
                .root("")
                .path(sources + "ifpb/gpes/domain/LambdaAndAnonymous.java") // root
                .sources(sources) // root - não obrigatorio
                .filter(".java");

        return Parse.with(ParseStrategies.JDT).from(project);
    }

}
