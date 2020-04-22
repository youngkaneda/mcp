package ifpb.gpes.jdt;

import ifpb.gpes.Call;
import ifpb.gpes.Parse;
import ifpb.gpes.Project;
import java.util.List;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author juan
 */
public class SampleObjectTest {

    private static List<Call> calls() {
        Project project = Project.root("")
                .path("../mcp-samples/src/main/java/ifpb/gpes/domain/SampleObject.java")
                .sources("../mcp-samples/src/main/java/")
                .filter(".java");

        return Parse.with(ParseStrategies.JDT).from(project);
    }

    @Test
    public void sampleTest() {
        List<Call> samples = calls();

        assertThat(samples, hasItems(
                Call.of("ifpb.gpes.domain.HasJCFObject", "getElements[]", "java.util.List<ifpb.gpes.domain.HasJCFObject>", "ifpb.gpes.domain.SampleObject", "teste[]", "void", "add[ifpb.gpes.domain.HasJCFObject]", "a"),
                Call.of("java.util.List", "add[ifpb.gpes.domain.HasJCFObject]", "boolean", "ifpb.gpes.domain.SampleObject", "teste[]", "void", null, "a.getElements()")
        ));
        assertEquals(2, samples.size());
    }

}
