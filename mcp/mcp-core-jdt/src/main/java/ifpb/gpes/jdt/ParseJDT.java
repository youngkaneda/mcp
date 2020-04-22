package ifpb.gpes.jdt;


import ifpb.gpes.ParseStrategy;
import org.eclipse.jdt.core.dom.ASTVisitor;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 06/07/2017, 19:07:25
 */
public interface ParseJDT extends ParseStrategy {

    public ParseJDT visitor(ASTVisitor visitor);
}
