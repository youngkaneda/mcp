package ifpb.gpes.jdt;

import ifpb.gpes.Call;
import ifpb.gpes.Parse;
import ifpb.gpes.Project;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import static org.junit.Assert.assertThat;

public class MethodReferenceTest {

    private final List<Call> result = ofMethodReferenceClass();
    private static final Logger logger = Logger.getLogger(MethodReferenceTest.class.getName());
    private static final String sources = "../mcp-samples/src/main/java/";

    @Test
    public void testeChamadaComReference() { }

    @Test
    public void testM1() {
        List<Call> expected = ofListM1();
        assertThat(result, hasItems(
                Call.of("java.io.PrintStream; println[? super ifpb.gpes.domain.HasJCFObject]; void; java.util.function.Consumer<? super ifpb.gpes.domain.HasJCFObject>; accept[ifpb.gpes.domain.HasJCFObject]; void; null; System.out"),
                Call.of("java.util.List; size[]; int; ifpb.gpes.domain.MethodReferenceExample; m1[java.util.function.Predicate<ifpb.gpes.domain.HasJCFObject>]; void; null; new HasJCFObject().getElements()")
        ));
    }

    private List<Call> ofListM1() {
        return Arrays.asList();
    }

    private List<Call> ofMethodReferenceClass() {
        Project project = Project
                .root("")
                .path(sources + "ifpb/gpes/domain/MethodReferenceExample.java") // root
                .sources(sources) // root - não obrigatorio
                .filter(".java");

        return Parse.with(ParseStrategies.JDT).from(project);

    }

}
