package ifpb.gpes;

import ifpb.gpes.jdt.ParseStrategies;
import ifpb.gpes.jdt.PrintASTVisitor;
import ifpb.gpes.jdt.SmartParseJDT;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ricardo Job
 */
public class ProjectWithJDTTest {

    private static final Logger logger = Logger.getLogger(ProjectWithJDTTest.class.getName());
    private static final String sources = "../mcp-samples/src/main/java/";

    @Test
    public void testProjectDefault() {
        Project project = Project
                .root("")
                .path(sources + "ifpb/gpes/domain/AnonymousClass.java") // root
                .sources(sources) // root - não obrigatorio
                .filter(".java");

        Parse parse = Parse
                .with(ParseStrategies.JDT);

        List<Call> result = parse.from(project);

        assertNotNull(project);
        assertNotNull(parse);
        assertNotNull(result);
        // log
        result.forEach(no -> logger.log(Level.INFO, no.callGraph()));

    }

    @Test
    public void testProjectOtherVisit() {
        Project project = Project
                .root("")
                .path(sources + "ifpb/gpes/domain/AnonymousClass.java") // root
                .sources(sources)// root - não obrigatorio
                .filter(".java");

        PrintASTVisitor visit = new PrintASTVisitor();
        ParseStrategy visitor = SmartParseJDT.of(visit);
        List<Call> returnList = Parse.with(visitor).from(project);

        assertNotNull(project);
        assertNotNull(visitor);
        assertNotNull(returnList);
        assertTrue(returnList.isEmpty());
        assertFalse("".equals(visit.visitToString().trim()));

        logger.log(Level.INFO, "---Visitor begin---");
        logger.log(Level.INFO, visit.visitToString());
        logger.log(Level.INFO, "---Visitor end  ---");
    }

}
