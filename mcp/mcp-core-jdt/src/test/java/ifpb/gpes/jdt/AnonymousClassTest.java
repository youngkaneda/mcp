package ifpb.gpes.jdt;

import ifpb.gpes.Call;
import ifpb.gpes.Parse;
import ifpb.gpes.Project;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.*;
import org.hamcrest.collection.IsIterableContainingInOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class AnonymousClassTest {

    private final List<Call> result = ofAnonymousClass();
    private static final Logger logger = Logger.getLogger(AnonymousClassTest.class.getName());
    private static final String sources = "../mcp-samples/src/main/java/";

    @Test
    public void testM1() {
        List<Call> expected = ofListM1();

        assertThat(result, hasItems(Call.of("java.util.List", "add[ifpb.gpes.domain.HasJCFObject]", "boolean", "ifpb.gpes.domain.SampleObject", "m2[]", "void", null, "new HasJCFObject().getElements()"),
                Call.of("java.util.List", "remove[java.lang.Object]", "boolean", "ifpb.gpes.domain.SampleObject", "m3[]", "void", null, "a.getElements()"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "getElements[]", "java.util.List<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.SampleObject", "m3[]", "void", "isEmpty[]", "a"),
                Call.of("java.util.List", "hashCode[]", "int", "ifpb.gpes.domain.AnonymousClass", "m1[]", "void", null, "new HasJCFObject().getElements()"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "getElements[]", "java.util.List<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.AnonymousClass", "m1[]", "void", "hashCode[]", "new HasJCFObject()")
        ));

        assertEquals(result.size(), expected.size());
        assertThat(result, is(expected));
        assertThat(result, IsIterableContainingInOrder.contains(expected.toArray()));

        result.forEach(no -> logger.log(Level.INFO, no.callGraph()));

    }

    private List<Call> ofListM1() {
        return Arrays.asList(
                Call.of("java.util.List", "add[ifpb.gpes.domain.HasJCFObject]", "boolean", "ifpb.gpes.domain.SampleObject", "m2[]", "void", null, "new HasJCFObject().getElements()"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "getElements[]", "java.util.List<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.SampleObject", "m2[]", "void", "add[ifpb.gpes.domain.HasJCFObject]", "new HasJCFObject()"),
                Call.of("java.util.List", "remove[java.lang.Object]", "boolean", "ifpb.gpes.domain.SampleObject", "m3[]", "void", null, "a.getElements()"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "getElements[]", "java.util.List<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.SampleObject", "m3[]", "void", "remove[java.lang.Object]", "a"),
                Call.of("java.util.List", "isEmpty[]", "boolean", "ifpb.gpes.domain.SampleObject", "m3[]", "void", null, "a.getElements()"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "getElements[]", "java.util.List<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.SampleObject", "m3[]", "void", "isEmpty[]", "a"),
                Call.of("java.util.List", "iterator[]", "java.util.Iterator<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.SampleObject", "m2[]", "void", null, "new HasJCFObject().getElements()"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "getElements[]", "java.util.List<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.SampleObject", "m2[]", "void", "iterator[]", "new HasJCFObject()"),
                Call.of("java.util.List", "listIterator[]", "java.util.ListIterator<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.SampleObject", "m2[]", "void", null, "new HasJCFObject().getElements()"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "getElements[]", "java.util.List<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.SampleObject", "m2[]", "void", "listIterator[]", "new HasJCFObject()"),
                Call.of("java.util.List", "iterator[]", "java.util.Iterator<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.Interface", "semRetorno[]", "void", null, "new HasJCFObject().getElements()"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "getElements[]", "java.util.List<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.Interface", "semRetorno[]", "void", "iterator[]", "new HasJCFObject()"),
                Call.of("java.util.List", "hashCode[]", "int", "ifpb.gpes.domain.AnonymousClass", "m1[]", "void", null, "new HasJCFObject().getElements()"),
                Call.of("ifpb.gpes.domain.HasJCFObject", "getElements[]", "java.util.List<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.AnonymousClass", "m1[]", "void", "hashCode[]", "new HasJCFObject()")
        );

    }

    private List<Call> ofAnonymousClass() {
        Project project = Project
                .root("")
                .path(sources + "ifpb/gpes/domain/AnonymousClass.java") // root
                .sources(sources) // root - não obrigatorio
                .filter(".java");

        return Parse.with(ParseStrategies.JDT).from(project);

    }

}
