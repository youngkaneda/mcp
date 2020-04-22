package ifpb.gpes.jdt;

import ifpb.gpes.Call;
import ifpb.gpes.Project;
import java.util.ArrayList;
import java.util.List;
import org.eclipse.jdt.core.dom.ASTVisitor;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 06/07/2017, 19:08:28
 */
public class SmartParseJDT implements ParseJDT {

    private List<Call> elements = new ArrayList<>();
    private ASTVisitor visitor;

    //TODO: isso não está bom. Just do It!
    public SmartParseJDT() {
        this.visitor = new DefaultVisitor(elements);
    }

    private SmartParseJDT(ASTVisitor visitor) {
        this.visitor = visitor;
    }

    public static ParseJDT of(ASTVisitor visitor) {
        return new SmartParseJDT(visitor);
    }

    @Override
    public List<Call> from(Project project) {
        DefaultASTParser parser = DefaultASTParser.from(project.sources());
        project.files().forEach(p -> {
            parser.updateUnitName(p);
            parser.acceptVisitor(visitor);
        });
        return elements;
    }

    @Override
    public ParseJDT visitor(ASTVisitor visitor) {
        this.visitor = visitor;
        return this;
    }
}
